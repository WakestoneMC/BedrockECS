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
        val literalValuesCount = orderedValues.size + 1 // runtimeId

        val templateLiteral = (0 until literalValuesCount).joinToString(",") { "%L" }
        val template = "%T($templateLiteral)"
        val templateValues = arrayOf<Any>(typeName, inst.runtimeId, *(orderedValues.toTypedArray()))

        val name = "instance${inst.runtimeId}"
        val literal = CodeBlock.builder().add(template, *templateValues).build()

        name to literal
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

    type.addType(companion)

    run {
        val primaryConstructor = FunSpec.constructorBuilder()

        primaryConstructor.addModifiers(KModifier.PRIVATE)

        var properties = orderedPropertyNames.map { pname ->
            val propertyName = snakeCaseToSmallCamelCase(pname)
            val propertyType = def.blockStateProps[pname]!!
            propertyName to propertyType
        }
        properties = listOf("runtimeId" to Short::class) + properties

        properties.forEach { (propertyName, propertyType) ->
            primaryConstructor.addParameter(
                ParameterSpec.builder(propertyName, propertyType)
                    .build()
            )
            type.addProperty(
                PropertySpec.builder(propertyName, propertyType)
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
                    .defaultValue(propertyName)
                    .build()
            )
        }

        with.returns(typeName)

        val code = CodeBlock.builder()
        val ctorParams = orderedPropertyNames.joinToString(",")
        code.addStatement("val e = %T($ctorParams)", typeName)
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
            code.addStatement("ret = ret && (this.$propName == other.$propName)")
        }
        code.addStatement("return ret")
        compare.addCode(code.build())

        type.addFunction(compare.build())
    }

    type.addProperty(
        PropertySpec.builder("blockType", String::class.asTypeName())
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
            PropertySpec.builder(name, typeName, KModifier.PRIVATE)
                .initializer(literal)
                .build()
        )
    }

    companion.addProperty(
        PropertySpec.builder("primary", typeName, KModifier.OVERRIDE)
            .initializer(instanceNameLiterals[0].first)
            .build()
    )

    companion.addProperty(
        PropertySpec.builder("allInstances", typeName, KModifier.OVERRIDE)
            .initializer(allInstanceLiteral)
            .build()
    )

    return companion.build()
}

fun codegenBlockTypes(blockTypes: List<ClassName>): FileSpec {
    val className = "VanillaBlockTypes"
    val typeName = ClassName(BLOCKS_BASE_PACKAGE, className)

    val type = TypeSpec.objectBuilder(typeName.simpleName)

    val outVanillaItem = WildcardTypeName.producerOf(VANILLA_BLOCK_TYPE)
    val classesType = List::class.asTypeName().parameterizedBy(KClass::class.asTypeName().parameterizedBy(outVanillaItem))
    type.addProperty("CLASSES", classesType)
    val typesType = List::class.asTypeName().parameterizedBy(VANILLA_BLOCK_TYPE)
    type.addProperty("TYPES", typesType)

    val classesTypesInitializer = CodeBlock.builder()
    classesTypesInitializer.addStatement("val classes = mutableListOf<%T>()", classesType)
    classesTypesInitializer.addStatement("val types = mutableListOf<%T>()", typesType)
    blockTypes.forEach {
        classesTypesInitializer.addStatement("classes.add(%T::class)", it)
        classesTypesInitializer.addStatement("types.addAll(%T.allInstances)", it)
    }
    classesTypesInitializer.add("CLASSES = classes")
    classesTypesInitializer.add("TYPES = types.sortedBy { it.runtimeID }")
    type.addInitializerBlock(classesTypesInitializer.build())

    return FileSpec.builder(typeName.packageName, typeName.simpleName)
        .addFileComment(GENERATED_FILE_COMMENT)
        .addType(type.build())
        .build()
}
