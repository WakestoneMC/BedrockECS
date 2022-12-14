package com.github.bedrockecs.common.palette

interface BitArray {
    operator fun set(index: Int, value: Int)
    operator fun get(index: Int): Int
    fun size(): Int
    val words: IntArray
    val version: BitArrayVersion
    fun copy(): BitArray
}
