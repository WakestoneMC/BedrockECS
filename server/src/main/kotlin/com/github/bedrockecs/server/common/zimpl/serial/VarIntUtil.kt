package com.github.bedrockecs.server.common.zimpl.serial

import io.netty.buffer.ByteBuf
import kotlin.experimental.or

fun ByteBuf.writeZigZagVarInt(value: Int) {
    writeUnsignedVarInt(encodeZigZag32(value))
}

fun encodeZigZag32(v0: Int): Long {
    val v = v0.toLong()
    // Note:  the right-shift must be arithmetic
    return ((v shl 1) xor (v shr 31)) and 0xFFFFFFFFL
}

fun ByteBuf.writeUnsignedVarInt(valueArg: Long) {
    var value = valueArg
    do {
        var temp = (value and 0b01111111).toByte()
        // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
        value = value ushr 7
        if (value != 0L) {
            temp = temp or (0b10000000).toByte()
        }
        writeByte(temp.toInt())
    } while (value != 0L)
}
