package com.github.bedrockecs.datacompiler.playground // ktlint-disable filename

import com.github.bedrockecs.datacompiler.parse.parse
import org.jetbrains.kotlinx.dataframe.io.writeCSV

fun main() {
    GENERATED_DIR.toFile().mkdir()

    val parsed = parse()

    parsed.blockStates.writeCSV(GENERATED_DIR.resolve("parsed-canon-blockstate-nbt.csv").toFile())
    parsed.requiredItems.writeCSV(GENERATED_DIR.resolve("required-items.csv").toFile())
}
