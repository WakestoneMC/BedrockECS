package com.github.bedrockecs.datacompiler.parse

fun parse(): ParseResult {
    val canonStream = CanonicalBlockStatesNBT.binaryStream()
    val parsedCanon = CanonicalBlockStatesNBT.parse(canonStream)

    val blockIdsStream = BlockIDMap.binaryStream()
    val blockIds = BlockIDMap.parse(blockIdsStream)

    val itemIdsStream = ItemIDMap.binaryStream()
    val itemIds = ItemIDMap.parse(itemIdsStream)

    return ParseResult(blockIds, parsedCanon, itemIds)
}
