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
 * parsed required_item_list.json
 */
@DataSchema
data class RequiredItem(
    val persisentName: String,
    val runtimeId: Int,
    val componentBased: Boolean
)

data class ParseResult(
    val requiredItems: DataFrame<RequiredItem>,
    val blockStates: DataFrame<ParsedBlockState>
)
