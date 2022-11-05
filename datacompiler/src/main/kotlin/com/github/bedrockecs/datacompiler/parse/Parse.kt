package com.github.bedrockecs.datacompiler.parse

fun parse(): ParseResult {
    val canonStream = CanonicalBlockStatesNBT.binaryStream()
    val parsedCanon = CanonicalBlockStatesNBT.parse(canonStream)

    val requiredStream = RequiredItemIDList.binaryStream()
    val requiredItems = RequiredItemIDList.parse(requiredStream)

    return ParseResult(requiredItems, parsedCanon)
}
