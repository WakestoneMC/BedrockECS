package com.github.bedrockecs.datacompiler.codegen // ktlint-disable filename

import com.github.bedrockecs.datacompiler.snakeCaseToBigCamelCase

fun persistentNameToClassName(name: String): String {
    var ret = name.replace("minecraft:", "")
    ret = snakeCaseToBigCamelCase(ret)
    return ret
}
