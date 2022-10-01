package com.github.bedrockecs.datacompiler.parse

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import java.io.InputStream

object BlockIDMap {
    fun binaryStream() = BlockIDMap::class.java.classLoader.getResourceAsStream("block_id_map.json")!!

    fun parse(stream: InputStream): DataFrame<ParsedBlockID> {
        val jsonString = String(stream.readAllBytes())
        val json = Json.decodeFromString(JsonElement.serializer(), jsonString) as JsonObject

        return json
            .map { (pname, id) ->
                val iid = (id as JsonPrimitive).content.toInt()
                ParsedBlockID(pname, iid)
            }
            .toDataFrame()
    }
}
