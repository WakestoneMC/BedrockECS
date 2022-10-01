package com.github.bedrockecs.datacompiler.analyze

import com.github.bedrockecs.datacompiler.parse.ParseResult
import org.jetbrains.kotlinx.dataframe.DataFrame

fun analyze(parsed: ParseResult): AnalysisResult {
    return AnalysisResult(
        DataFrame.emptyOf(),
        DataFrame.emptyOf(),
        DataFrame.emptyOf()
    )
}
