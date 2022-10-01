package com.github.bedrockecs.datacompiler.playground // ktlint-disable filename

import com.github.bedrockecs.datacompiler.parse.parse
import org.jetbrains.kotlinx.dataframe.io.writeCSV

fun main() {
    GENERATED_DIR.toFile().mkdir()

    val parsed = parse()

    parsed.blockStates.writeCSV(GENERATED_DIR.resolve("parsed-canon-blockstate-nbt.csv").toFile())
    parsed.blockIds.writeCSV(GENERATED_DIR.resolve("parsed-block-ids-map.csv").toFile())
    parsed.itemIds.writeCSV(GENERATED_DIR.resolve("parsed-item-ids-map.csv").toFile())
}
