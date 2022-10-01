package com.github.bedrockecs.datacompiler.parse

import cn.nukkit.nbt.NBTIO
import cn.nukkit.nbt.tag.CompoundTag
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import java.io.BufferedInputStream
import java.io.InputStream
import java.nio.ByteOrder

object CanonicalBlockStatesNBT {
    fun binaryStream() = CanonicalBlockStatesNBT::class.java.classLoader
        .getResourceAsStream("canonical_block_states.nbt")!!

    fun parse(stream: InputStream): DataFrame<ParsedBlockState> {
        val entries: MutableList<ParsedBlockState> = mutableListOf()
        BufferedInputStream(stream).use { bis ->
            var runtimeId = 0
            while (bis.available() > 0) {
                val tag: CompoundTag = NBTIO.read(bis, ByteOrder.BIG_ENDIAN, true)
                assert(tag.tags.keys == setOf("name", "version", "states")) { "unexpected BlockState Entry ${tag.tags.keys}" }
                entries.add(
                    ParsedBlockState(
                        persisentName = tag.getString("name"),
                        version = tag.getInt("version"),
                        states = tag.getCompound("states"),
                        runtimeId = runtimeId++
                    )
                )
            }
        }
        return entries.toDataFrame()
    }
}
