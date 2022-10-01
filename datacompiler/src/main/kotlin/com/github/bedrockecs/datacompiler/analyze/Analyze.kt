package com.github.bedrockecs.datacompiler.analyze

import com.github.bedrockecs.datacompiler.parse.ParseResult
import com.github.bedrockecs.datacompiler.parse.id
import com.github.bedrockecs.datacompiler.parse.persisentName
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.map
import org.jetbrains.kotlinx.dataframe.api.toDataFrame

fun analyze(parsed: ParseResult): AnalysisResult {
    val itemDefs = analyzeItems(parsed)

    return AnalysisResult(
        itemDefs,
        DataFrame.emptyOf(),
        DataFrame.emptyOf()
    )
}

fun analyzeItems(parsed: ParseResult): DataFrame<ItemTypeDefinition> {
    return parsed.itemIds
        .map { ItemTypeDefinition(persistentName = it.persisentName, itemId = it.id) }
        .toDataFrame()
}
