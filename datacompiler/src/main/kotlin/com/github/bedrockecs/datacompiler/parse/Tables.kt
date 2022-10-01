package com.github.bedrockecs.datacompiler.parse

import cn.nukkit.nbt.tag.CompoundTag
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.annotations.DataSchema

/**
 * parsed canonical_block_state.nbt table
 */
@DataSchema
data class ParsedBlockState(
    val persisentName: String,
    val version: Int,
    val states: CompoundTag,
    val runtimeId: Int // primary key
)

/**
 * parsed block_id_map.json table
 */
@DataSchema
data class ParsedBlockID(
    val persisentName: String,
    val id: Int
)

/**
 * parsed item_id_map.json table
 */
@DataSchema
data class ParsedItemID(
    val persisentName: String,
    val id: Int
)

data class ParseResult(
    val blockIds: DataFrame<ParsedBlockID>,
    val blockStates: DataFrame<ParsedBlockState>,
    val itemIds: DataFrame<ParsedItemID>
)
