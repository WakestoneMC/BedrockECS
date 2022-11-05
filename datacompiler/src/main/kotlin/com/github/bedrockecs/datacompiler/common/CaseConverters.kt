package com.github.bedrockecs.datacompiler

fun snakeCaseToBigCamelCase(str: String): String {
    return str
        .split("_")
        .map { it.substring(0, 1).uppercase() + it.substring(1) }
        .joinToString("")
}

fun snakeCaseToAllCapsCase(str: String): String {
    return str
        .split("_")
        .map { it.uppercase() }
        .joinToString("_")
}

fun bigCamelCaseToSnakeCase(str: String): String {
    val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
    return camelRegex.replace(str) {
        "_${it.value}"
    }.lowercase()
}

fun snakeCaseToSmallCamelCase(str: String): String {
    val ret = snakeCaseToBigCamelCase(str)
    return ret.substring(0, 1).lowercase() + ret.substring(1)
}
