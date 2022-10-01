/**
 * central point of definition for all data tables in the compiler pipeline
 */
package com.github.bedrockecs.datacompiler

import kotlin.reflect.KClass

/**
 * represents a block type definition
 */
typealias BlockTypeTable = List<BlockTypeRow>

data class BlockTypeRow(
    /**
     * snake_case name of the type
     */
    val componentName: String // primary key
)

/**
 * association table between [BlockTypeTable] and [ParsedBlockStateTable]
 */
typealias BlockTypeParsedStateTable = List<Pair<String, Int>>

/**
 * represents a block state definition
 */
typealias BlockStateDefinitionTable = List<BlockStateDefinitionRow>

data class BlockStateDefinitionRow(
    /**
     * snake_case name of the type
     */
    val componentName: String,
    val propertyTypes: Map<String, KClass<*>>
)

/**
 * association table between [BlockStateDefinitionTable] and [ParsedBlockStateTable]
 */
typealias BlockStateDefinitionParsedStateTable = List<Pair<String, Int>>
