package com.github.bedrockecs.server.comm.zimpl.handler

import com.github.bedrockecs.server.common.zimpl.palette.PaletteStorage
import com.github.bedrockecs.server.game.data.ChunkPosition
import com.github.bedrockecs.server.game.db.world.serial.SerialChunk
import com.github.bedrockecs.server.game.db.world.serial.SerialSubChunkLayer
import com.github.bedrockecs.vanilla.blocks.world.AirBlockType
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled

object GameChunkSerializer {

    fun serializeChunk(pos: ChunkPosition, chunk: SerialChunk): LevelChunkPacket {
        val packet = LevelChunkPacket()
        packet.isCachingEnabled = false
        packet.blobIds.clear()

        packet.subChunkLimit = 0
        packet.isRequestSubChunks = false

        packet.subChunksLength = chunk.subChunks.size
        packet.chunkX = pos.x
        packet.chunkZ = pos.z

        packet.data = serializeSubChunkPayload(chunk).array()

        return packet
    }

    private fun serializeSubChunkPayload(chunk: SerialChunk): ByteBuf {
        val biome = computeBiomePalette()
        val buf = Unpooled.buffer()

        // chunk content
        for (subchunk in chunk.subChunks) {
            buf.writeByte(8)
            buf.writeByte(subchunk.layers.size)
            for (layer in subchunk.layers) {
                serializeSubChunkLayer(layer).writeTo(buf)
            }
        }

        // biome, TODO: actually implement this
        for (x in 0..24) {
            biome.writeTo(buf)
        }

        // border block
        buf.writeByte(0)

        // block entity nbt, TODO: actually implement this

        return buf
    }

    private fun computeBiomePalette(): PaletteStorage {
        val airRID = 0
        val storage = PaletteStorage.createWithDefaultState(airRID)
        return storage
    }

    private fun serializeSubChunkLayer(layer: SerialSubChunkLayer): PaletteStorage {
        val airRID = AirBlockType.allInstances[0].runtimeID.toInt()
        val storage = PaletteStorage.createWithDefaultState(airRID)
        when (layer) {
            is SerialSubChunkLayer.UnPalettedShort -> {
                for (x in 0..15) {
                    for (y in 0..15) {
                        for (z in 0..15) {
                            storage.setBlock(x, y, z, layer.idAtOffset(x, y, z).toInt())
                        }
                    }
                }
                return storage
            }
        }
    }
}
