package com.github.bedrockecs.comm.zimpl.serial

import com.github.bedrockecs.common.palette.PalettedStorage
import com.github.bedrockecs.game.data.ChunkPosition
import com.github.bedrockecs.game.db.world.serial.SerialChunk
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
        packet.chunkX = pos.chunkX
        packet.chunkZ = pos.chunkZ

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
                layer.storage.writeTo(buf)
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

    private fun computeBiomePalette(): PalettedStorage {
        val airRID = 0
        val storage = PalettedStorage.createWithDefaultState(airRID)
        return storage
    }
}
