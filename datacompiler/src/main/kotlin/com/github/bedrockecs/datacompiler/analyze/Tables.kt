package com.github.bedrockecs.datacompiler.analyze

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import kotlin.reflect.KClass

/**
 * definition of item type
 */
@DataSchema
data class ItemTypeDefinition(
    val persistentName: String, // primary key
    val itemId: Int
)

/**
 * definition of block type
 */
@DataSchema
data class BlockTypeDefinition(
    val persistentName: String, // primary key
    val blockId: String,
    val blockStateProps: Map<String, KClass<*>>
)

/**
 * instance of a block type, represents one possible combination of block states
 */
@DataSchema
data class BlockTypeInstance(
    val persistentName: String, // primary key, foreign key to : [BlockTypeDefinition.persistentName]
    val runtimeId: Int,
    val blockStatePropValues: Map<String, Any>
)

data class AnalysisResult(
    val itemDefs: DataFrame<ItemTypeDefinition>,
    val blockDefs: DataFrame<BlockTypeDefinition>,
    val blockInstances: DataFrame<BlockTypeInstance>
)
