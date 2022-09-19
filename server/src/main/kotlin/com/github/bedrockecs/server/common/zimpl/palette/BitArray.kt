package com.github.bedrockecs.server.common.zimpl.palette

interface BitArray {
    operator fun set(index: Int, value: Int)
    operator fun get(index: Int): Int
    fun size(): Int
    val words: IntArray
    val version: BitArrayVersion
    fun copy(): BitArray
}
