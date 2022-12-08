package com.github.bedrockecs.common.palette

import com.github.bedrockecs.common.serial.writeZigZagVarInt
import io.netty.buffer.ByteBuf
import it.unimi.dsi.fastutil.ints.IntArrayList
import it.unimi.dsi.fastutil.ints.IntList

/**
 * PalettedBlockStorage, a (16*16*16)? storage of runtimeId, using local id internally
 */
class PalettedStorage {
    /**
     * palette local id - runtimeId look up table, content is runtimeId, index is palette local id, local id = 0 is the default state
     */
    private val palette: IntList

    /**
     * packed bit array to store all the blocks in the layer using palette local id
     */
    private var bitArray: BitArray?
    // ctors //
    /**
     * initialize with given version of palette, set defaultState
     */
    private constructor(version: BitArrayVersion, defaultState: Int) {
        bitArray = version.createPalette(SIZE)
        palette = IntArrayList(16)
        palette.add(defaultState)
    }

    private constructor(bitArray: BitArray?, palette: IntList) {
        this.palette = palette
        this.bitArray = bitArray
    }

    /*
     * palette header: versionIds | runtime or not(lowest bit)
     */
    private fun getPaletteHeader(version: BitArrayVersion?, runtime: Boolean): Int {
        return version!!.id.toInt() shl 1 or if (runtime) 1 else 0
    }

    /**
     * position x(4bit) | z(4bit) | y(4bit)
     */
    private fun getIndex(x: Int, y: Int, z: Int): Int {
        return x shl 8 or (z shl 4) or y
    }

    /**
     * set value at location as runtimeId
     */
    fun setBlock(x: Int, y: Int, z: Int, runtimeId: Int) {
        this.setBlock(getIndex(x, y, z), runtimeId)
    }

    /**
     * set value at location as runtimeId
     */
    fun setBlock(index: Int, runtimeId: Int) {
        try {
            // get the local id for this runtimeId
            val id = idFor(runtimeId)
            // write the local id into bitarray with index (computed from x/y/z coordinate)
            bitArray!![index] = id
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Unable to set block runtime ID: $runtimeId, palette: $palette", e)
        }
    }

    fun getBlock(x: Int, y: Int, z: Int): Int {
        return this.getBlock(getIndex(x, y, z))
    }

    fun getBlock(index: Int): Int {
        val localID = bitArray!![index]
        return palette.getInt(localID)
    }

    private fun idFor(runtimeId: Int): Int {
        // lookup runtimeId in the palette, get index and use it as id if exists
        var index = palette.indexOf(runtimeId)
        if (index != -1) {
            return index
        }

        // insert runtimeId into palette
        index = palette.size
        // resize bit array once it cannot hold every local id
        val version = bitArray!!.version
        if (index > version.maxEntryValue) {
            val next = version!!.next()
            if (next != null) {
                onResize(next)
            }
        }
        // add the runtimeId to palette
        palette.add(runtimeId)
        return index
    }

    fun writeTo(stream: ByteBuf) {
        stream.writeByte(getPaletteHeader(bitArray!!.version, true))
        bitArray!!.words.forEach { stream.writeIntLE(it) }
        stream.writeZigZagVarInt(palette.size)
        palette.forEach { stream.writeZigZagVarInt(it) }
    }

    private fun onResize(version: BitArrayVersion) {
        val newBitArray = version.createPalette(SIZE)
        for (i in 0 until SIZE) {
            newBitArray[i] = bitArray!![i]
        }
        bitArray = newBitArray
    }

    // general operation //

    val isEmpty: Boolean
        get() {
            if (palette.size == 1) {
                return true
            }
            for (word in bitArray!!.words) {
                if (Integer.toUnsignedLong(word) != 0L) {
                    return false
                }
            }
            return true
        }

    fun copy(): PalettedStorage {
        return PalettedStorage(bitArray!!.copy(), IntArrayList(palette))
    }

    companion object {
        private const val SIZE = 4096

        fun createWithDefaultState(defaultState: Int): PalettedStorage {
            return createWithDefaultState(BitArrayVersion.V2, defaultState)
        }

        fun createWithDefaultState(version: BitArrayVersion, defaultState: Int): PalettedStorage {
            return PalettedStorage(version, defaultState)
        }
    }
}
