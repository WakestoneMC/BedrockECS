package com.github.bedrockecs.datacompiler.codegen

import com.github.bedrockecs.datacompiler.analyze.BlockTypeDefinition
import com.github.bedrockecs.datacompiler.analyze.BlockTypeInstance
import com.github.bedrockecs.datacompiler.analyze.blockStatePropValues
import com.github.bedrockecs.datacompiler.analyze.blockStateProps
import com.github.bedrockecs.datacompiler.analyze.itemId
import com.github.bedrockecs.datacompiler.analyze.persistentName
import com.github.bedrockecs.datacompiler.analyze.runtimeId
import com.github.bedrockecs.datacompiler.snakeCaseToSmallCamelCase
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.WildcardTypeName
import com.squareup.kotlinpoet.asTypeName
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.DataRow
import org.jetbrains.kotlinx.dataframe.api.map
import kotlin.reflect.KClass

fun codeGenBlockType(
    def: DataRow<BlockTypeDefinition>,
    instances: DataFrame<BlockTypeInstance>
): Pair<FileSpec, ClassName> {
    // compute //
    val className = persistentNameToClassName(def.persistentName) + "BlockType"
    val typeName = ClassName(BLOCKS_BASE_PACKAGE, className)

    val itemID = if (def.itemId == null) {
        log.warn("giving 0 as replacement of unknown item id for block def [def={}]", def)
        0
    } else {
        def.itemId!!
    }

    val orderedPropertyNames = def.blockStateProps.keys.sorted()

    val instanceNameLiterals = instances.map { inst ->
        val orderedValues = orderedPropertyNames.map { inst.blockStatePropValues[it]!! }

        val literal = with(StringBuilder()) {
            append(typeName.simpleName)
            append("(")

            append(primitiveToLiteral(inst.runtimeId.toShort()))
            orderedValues.forEach { v ->
                append(", ")
                append(primitiveToLiteral(v))
            }

            append(")")
        }.toString()

        val name = "instance${inst.runtimeId}"
        val code = CodeBlock.builder().add(literal).build()

        name to code
    }

    val allInstanceLiteral = "listOf(${instanceNameLiterals.joinToString(",") { it.first }})"

    // emit //

    val companion = emitCompanion(def, itemID, instanceNameLiterals, typeName, allInstanceLiteral)
    val type = emitClass(typeName, companion, orderedPropertyNames, def)

    val file = FileSpec.builder(typeName.packageName, typeName.simpleName)
        .addFileComment(GENERATED_FILE_COMMENT)
        .addType(type)
        .build()

    return file to typeName
}

private fun emitClass(
    typeName: ClassName,
    companion: TypeSpec,
    orderedPropertyNames: List<String>,
    def: DataRow<BlockTypeDefinition>
): TypeSpec {
    val type = TypeSpec.classBuilder(typeName)

    type.addModifiers(KModifier.DATA)

    type.addAnnotation(Serializable::class)

    type.addSuperinterface(VANILLA_BLOCK_TYPE)

    type.addType(companion)

    run {
        val primaryConstructor = FunSpec.constructorBuilder()

        primaryConstructor.addModifiers(KModifier.PRIVATE)

        var properties = orderedPropertyNames.map { pname ->
            val propertyName = snakeCaseToSmallCamelCase(pname)
            val propertyType = def.blockStateProps[pname]!!
            Triple(propertyName, propertyType, emptyArray<KModifier>())
        }
        properties = listOf(Triple("runtimeID", Short::class, arrayOf(KModifier.OVERRIDE))) + properties

        properties.forEach { (propertyName, propertyType, modifiers) ->
            primaryConstructor.addParameter(
                ParameterSpec.builder(propertyName, propertyType)
                    .build()
            )
            type.addProperty(
                PropertySpec.builder(propertyName, propertyType, *modifiers)
                    .initializer(propertyName)
                    .build()
            )
        }

        type.primaryConstructor(primaryConstructor.build())
    }

    run {
        val with = FunSpec.builder("with")

        orderedPropertyNames.forEach { pname ->
            val propertyName = snakeCaseToSmallCamelCase(pname)
            val propertyType = def.blockStateProps[pname]!!

            with.addParameter(
                ParameterSpec.builder(propertyName, propertyType)
                    .defaultValue("this.$propertyName")
                    .build()
            )
        }

        with.returns(typeName)

        val code = CodeBlock.builder()
        val ctorParams = orderedPropertyNames.joinToString(",") { snakeCaseToSmallCamelCase(it) }
        code.addStatement("val e = %T(0.toShort(), $ctorParams)", typeName)
        code.addStatement("return allInstances.find { it.compareVariantProperties(e) }!!")
        with.addCode(code.build())

        type.addFunction(with.build())
    }

    run {
        val compare = FunSpec.builder("compareVariantProperties")

        compare.addParameter("other", typeName)

        compare.returns(Boolean::class.asTypeName())

        val code = CodeBlock.builder()
        code.addStatement("var ret = true")
        orderedPropertyNames.forEach { propName ->
            val property = snakeCaseToSmallCamelCase(propName)
            code.addStatement("ret = ret && (this.$property == other.$property)")
        }
        code.addStatement("return ret")
        compare.addCode(code.build())

        type.addFunction(compare.build())
    }

    type.addProperty(
        PropertySpec.builder("blockType", String::class.asTypeName(), KModifier.OVERRIDE)
            .initializer("Companion.blockType")
            .build()
    )

    return type.build()
}

private fun emitCompanion(
    def: DataRow<BlockTypeDefinition>,
    itemID: Int,
    instanceNameLiterals: List<Pair<String, CodeBlock>>,
    typeName: ClassName,
    allInstanceLiteral: String
): TypeSpec {
    val companion = TypeSpec.companionObjectBuilder()

    companion.addSuperinterface(VANILLA_BLOCK_TYPE_COMPANION)

    companion.addProperty(
        PropertySpec.builder("blockType", String::class.asTypeName(), KModifier.OVERRIDE)
            .initializer("\"%L\"", def.persistentName)
            .build()
    )

    companion.addProperty(
        PropertySpec.builder("itemID", ITEM_ID, KModifier.OVERRIDE)
            .initializer("ItemID(%L)", itemID)
            .build()
    )

    instanceNameLiterals.forEach { (name, literal) ->
        companion.addProperty(
            PropertySpec.builder(name, typeName, KModifier.PRIVATE, KModifier.LATEINIT)
                .mutable(true)
                .build()
        )
    }

    instanceNameLiterals
        .chunked(256)
        .forEachIndexed { index, nameLiterals ->
            val funName = "init$index"

            val codeBlock = CodeBlock.builder()
            nameLiterals.forEach { (name, literal) ->
                codeBlock.addStatement("$name = $literal")
            }

            val func = FunSpec.builder(funName).addCode(codeBlock.build()).build()
            companion.addFunction(func)
            companion.addInitializerBlock(CodeBlock.of("$funName()"))
        }

    companion.addProperty(
        PropertySpec.builder("primary", typeName, KModifier.OVERRIDE)
            .initializer(instanceNameLiterals[0].first)
            .build()
    )

    companion.addProperty(
        PropertySpec.builder("allInstances", LIST.parameterizedBy(typeName), KModifier.OVERRIDE)
            .initializer(allInstanceLiteral)
            .build()
    )

    return companion.build()
}

private fun primitiveToLiteral(value: Any): String {
    return when (value) {
        // primitives
        is Byte -> "$value.toByte()"
        is Double -> "$value"
        is Float -> "${value}f"
        is Int -> "$value"
        is Long -> "${value}L"
        is Short -> "$value.toShort()"
        is String -> "\"$value\""
        else -> throw IllegalArgumentException("unexpected type: $value")
    }
}

fun codegenBlockTypes(blockTypes: List<ClassName>): FileSpec {
    val className = "VanillaBlockTypes"
    val typeName = ClassName(BLOCKS_BASE_PACKAGE, className)

    val type = TypeSpec.objectBuilder(typeName.simpleName)

    val classesElementType = KClass::class.asTypeName().parameterizedBy(WildcardTypeName.producerOf(VANILLA_BLOCK_TYPE))
    val classesType = List::class.asTypeName().parameterizedBy(classesElementType)
    type.addProperty("CLASSES", classesType)
    val typesElementType = VANILLA_BLOCK_TYPE
    val typesType = List::class.asTypeName().parameterizedBy(typesElementType)
    type.addProperty("TYPES", typesType)

    val classesTypesInitializer = CodeBlock.builder()
    classesTypesInitializer.addStatement("val classes = mutableListOf<%T>()", classesElementType)
    classesTypesInitializer.addStatement("val types = mutableListOf<%T>()", typesElementType)
    blockTypes.forEach {
        classesTypesInitializer.addStatement("classes.add(%T::class)", it)
        classesTypesInitializer.addStatement("types.addAll(%T.allInstances)", it)
    }
    classesTypesInitializer.addStatement("CLASSES = classes")
    classesTypesInitializer.addStatement("TYPES = types.sortedBy { it.runtimeID }")
    type.addInitializerBlock(classesTypesInitializer.build())

    return FileSpec.builder(typeName.packageName, typeName.simpleName)
        .addFileComment(GENERATED_FILE_COMMENT)
        .addType(type.build())
        .build()
}
