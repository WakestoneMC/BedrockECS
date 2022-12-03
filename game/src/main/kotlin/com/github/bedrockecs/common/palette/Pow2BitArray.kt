package com.github.bedrockecs.common.palette

import com.nukkitx.math.GenericMath
import com.nukkitx.network.util.Preconditions
import java.util.Arrays

class Pow2BitArray internal constructor(
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
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */

    init {
        val expectedWordsLength = GenericMath.ceil(size.toFloat() / version.entriesPerWord)
        require(words.size == expectedWordsLength) {
            "Invalid length given for storage, got: " + words.size +
                " but expected: " + expectedWordsLength
        }
    }

    /**
     * Sets the entry at the given location to the given value
     */
    override fun set(index: Int, value: Int) {
        Preconditions.checkElementIndex(index, size)
        Preconditions.checkArgument(
            value >= 0 && value <= version.maxEntryValue,
            "Max value: %s. Received value",
            version.maxEntryValue,
            value
        )
        val bitIndex = index * version.id
        val arrayIndex = bitIndex shr 5
        val offset = bitIndex and 31
        words[arrayIndex] =
            words[arrayIndex] and (version.maxEntryValue shl offset).inv() or (value and version.maxEntryValue shl offset)
    }

    /**
     * Gets the entry at the given index
     */
    override fun get(index: Int): Int {
        Preconditions.checkElementIndex(index, size)
        val bitIndex = index * version.id
        val arrayIndex = bitIndex shr 5
        val wordOffset = bitIndex and 31
        return words[arrayIndex] ushr wordOffset and version.maxEntryValue
    }

    /**
     * Gets the long array that is used to store the data in this BitArray. This is useful for sending packet data.
     */
    override fun size(): Int {
        return size
    }

    override fun copy(): BitArray {
        return Pow2BitArray(version, size, Arrays.copyOf(words, words.size))
    }
}
