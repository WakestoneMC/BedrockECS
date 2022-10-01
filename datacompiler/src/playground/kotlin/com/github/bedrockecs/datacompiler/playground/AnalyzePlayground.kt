package com.github.bedrockecs.datacompiler.playground

import com.github.bedrockecs.datacompiler.analyze.analyze
import com.github.bedrockecs.datacompiler.parse.parse
import org.jetbrains.kotlinx.dataframe.io.writeCSV

fun main() {
    GENERATED_DIR.toFile().mkdir()

    val parsed = parse()
    val analyzed = analyze(parsed)

    analyzed.blockDefs.writeCSV(GENERATED_DIR.resolve("analyzed-block-defs.csv").toFile())
    analyzed.blockInstances.writeCSV(GENERATED_DIR.resolve("analyzed-block-insts.csv").toFile())
    analyzed.itemDefs.writeCSV(GENERATED_DIR.resolve("analyzed-item-defs.csv").toFile())
}
