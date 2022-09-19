package com.github.bedrockecs.server.common.zimpl.palette

import com.nukkitx.network.util.Preconditions
import java.util.Arrays

class PaddedBitArray internal constructor(
    /**
     * Palette version information
     */
    override val version: BitArrayVersion,
    /**
     * Number of entries in this palette (**not** the length of the words array that internally backs this palette)
     */
    private val size: Int,
    /**
     * Array used to store data
     */
    override val words: IntArray
) : BitArray {

    init {
        val expectedWordsLength = ceil(size.toFloat() / version.entriesPerWord)
        require(words.size == expectedWordsLength) {
            "Invalid length given for storage, got: " + words.size +
                " but expected: " + expectedWordsLength
        }
    }

    override fun set(index: Int, value: Int) {
        Preconditions.checkElementIndex(index, size)
        Preconditions.checkArgument(
            value >= 0 && value <= version.maxEntryValue,
            "Max value: %s. Received value",
            version.maxEntryValue,
            value
        )
        val arrayIndex = index / version.entriesPerWord
        val offset = index % version.entriesPerWord * version.id
        words[arrayIndex] =
            words[arrayIndex] and (version.maxEntryValue shl offset).inv() or (value and version.maxEntryValue shl offset)
    }

    override fun get(index: Int): Int {
        Preconditions.checkElementIndex(index, size)
        val arrayIndex = index / version.entriesPerWord
        val offset = index % version.entriesPerWord * version.id
        return words[arrayIndex] ushr offset and version.maxEntryValue
    }

    override fun size(): Int {
        return size
    }

    override fun copy(): BitArray {
        return PaddedBitArray(version, size, Arrays.copyOf(words, words.size))
    }

    companion object {
        fun ceil(floatNumber: Float): Int {
            val truncated = floatNumber.toInt()
            return if (floatNumber > truncated) truncated + 1 else truncated
        }
    }
}
