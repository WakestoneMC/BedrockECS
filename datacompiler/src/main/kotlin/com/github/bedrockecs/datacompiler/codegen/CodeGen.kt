package com.github.bedrockecs.datacompiler.codegen

import com.github.bedrockecs.datacompiler.analyze.AnalysisResult
import com.squareup.kotlinpoet.FileSpec
import org.jetbrains.kotlinx.dataframe.api.map

fun codeGen(analyzed: AnalysisResult): List<FileSpec> {
    val ret = mutableListOf<FileSpec>()

    run {
        val itemTuples = analyzed.itemDefs.map { codegenItemType(it) }.toList()
        val itemDefs = itemTuples.map { it.first }
        val itemTypeNames = itemTuples.map { it.second }
        val itemTypes = codegenItemTypes(itemTypeNames)

        ret.addAll(itemDefs)
        ret.add(itemTypes)
    }

    return ret
}
