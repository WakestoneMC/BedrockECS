package com.github.bedrockecs.datacompiler.parse

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import java.io.InputStream

object ItemIDMap {
    fun binaryStream() = ItemIDMap::class.java.classLoader.getResourceAsStream("item_id_map.json")!!

    fun parse(stream: InputStream): DataFrame<ParsedItemID> {
        val jsonString = String(stream.readAllBytes())
        val json = Json.decodeFromString(JsonElement.serializer(), jsonString) as JsonObject

        return json
            .map { (pname, id) ->
                val iid = (id as JsonPrimitive).content.toInt()
                ParsedItemID(pname, iid)
            }
            .toDataFrame()
    }
}
