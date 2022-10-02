package com.github.bedrockecs.datacompiler.playground // ktlint-disable filename

import com.github.bedrockecs.datacompiler.analyze.analyze
import com.github.bedrockecs.datacompiler.codegen.codeGen
import com.github.bedrockecs.datacompiler.parse.parse

fun main() {
    GENERATED_DIR.toFile().mkdir()

    val parsed = parse()
    val analyzed = analyze(parsed)
    val generated = codeGen(analyzed)

    generated.forEach { it.writeTo(GENERATED_DIR) }
}
