package com.github.bedrockecs.datacompiler.codegen

import com.github.bedrockecs.datacompiler.analyze.ItemTypeDefinition
import com.github.bedrockecs.datacompiler.analyze.itemId
import com.github.bedrockecs.datacompiler.analyze.persistentName
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.WildcardTypeName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.dataframe.DataRow
import kotlin.reflect.KClass

fun codegenItemType(row: DataRow<ItemTypeDefinition>): Pair<FileSpec, ClassName> {
    // compute //

    val className = persistentNameToClassName(row.persistentName) + "ItemType"
    val typeName = ClassName(ITEMS_BASE_PACKAGE, className)

    // emit //

    val companion = TypeSpec.companionObjectBuilder()
    companion.addSuperinterface(VANILLA_ITEM_TYPE_COMPANION)
    companion.addProperty(
        PropertySpec.builder("itemID", ITEM_ID, KModifier.OVERRIDE)
            .initializer("ItemID(${row.itemId})")
            .build()
    )
    companion.addProperty(
        PropertySpec.builder("itemType", String::class.asClassName(), KModifier.OVERRIDE)
            .initializer("\"${row.persistentName}\"")
            .build()
    )

    val typeSpec = TypeSpec.classBuilder(typeName.simpleName)

    typeSpec.addAnnotation(Serializable::class.asClassName())

    typeSpec.addSuperinterface(VANILLA_ITEM_TYPE)

    typeSpec.addType(companion.build())

    typeSpec.addProperty(
        PropertySpec.builder("itemType", String::class.asClassName(), KModifier.OVERRIDE)
            .initializer("Companion.itemType")
            .build()
    )

    typeSpec.addFunction(
        FunSpec.builder("clone")
            .addModifiers(KModifier.OVERRIDE)
            .returns(typeName)
            .addCode("return this")
            .build()
    )

    val file = FileSpec.builder(typeName.packageName, typeName.simpleName)
        .addFileComment(GENERATED_FILE_COMMENT)
        .addType(typeSpec.build())
        .build()

    return file to typeName
}

fun codegenItemTypes(itemTypes: List<ClassName>): FileSpec {
    val className = "VanillaItemTypes"
    val typeName = ClassName(ITEMS_BASE_PACKAGE, className)

    val type = TypeSpec.objectBuilder(typeName.simpleName)

    val outVanillaItem = WildcardTypeName.producerOf(VANILLA_ITEM_TYPE)
    val classesType = List::class.asTypeName().parameterizedBy(KClass::class.asTypeName().parameterizedBy(outVanillaItem))
    type.addProperty("CLASSES", classesType)

    val classesInitializer = CodeBlock.builder()
    classesInitializer.addStatement("val types = mutableListOf<%T>()", classesType)
    itemTypes.forEach { classesInitializer.addStatement("types.add(%T::class)", it) }
    classesInitializer.add("CLASSES = types")
    type.addInitializerBlock(classesInitializer.build())

    return FileSpec.builder(typeName.packageName, typeName.simpleName)
        .addFileComment(GENERATED_FILE_COMMENT)
        .addType(type.build())
        .build()
}
