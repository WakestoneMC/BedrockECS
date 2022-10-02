package com.github.bedrockecs.datacompiler.parse

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import java.io.InputStream

object RequiredItemIDList {
    fun binaryStream() = RequiredItemIDList::class.java.classLoader.getResourceAsStream("required_item_list.json")!!

    fun parse(stream: InputStream): DataFrame<RequiredItem> {
        val jsonString = String(stream.readAllBytes())
        val json = Json.decodeFromString(JsonElement.serializer(), jsonString) as JsonObject

        return json
            .map { (pname, entry) ->
                entry as JsonObject
                val runtimeId = (entry["runtime_id"] as JsonPrimitive).content.toInt()
                val componentBased = (entry["component_based"] as JsonPrimitive).content.toBoolean()
                RequiredItem(pname, runtimeId, componentBased)
            }
            .toDataFrame()
    }
}
