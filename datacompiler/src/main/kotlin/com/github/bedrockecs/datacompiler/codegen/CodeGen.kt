package com.github.bedrockecs.datacompiler.codegen

import com.github.bedrockecs.datacompiler.analyze.AnalysisResult
import com.github.bedrockecs.datacompiler.analyze.BlockTypeInstance
import com.github.bedrockecs.datacompiler.analyze.persistentName
import com.squareup.kotlinpoet.FileSpec
import org.jetbrains.kotlinx.dataframe.api.groupBy
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

    run {
        val nameToInstances = analyzed.blockInstances
            .groupBy(BlockTypeInstance::persistentName).groups
            .map { it[0].persistentName to it }.toList()
            .toMap()

        val blockTuples = analyzed.blockDefs.map { def ->
            val instances = nameToInstances[def.persistentName]!!
            codeGenBlockType(def, instances)
        }
        val blockDefs = blockTuples.map { it.first }
        val blockTypeNames = blockTuples.map { it.second }

        val blockTypes = codegenBlockTypes(blockTypeNames)

        ret.addAll(blockDefs)
        ret.add(blockTypes)
    }

    return ret
}
