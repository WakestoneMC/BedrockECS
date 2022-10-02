package com.github.bedrockecs.vanilla.data.blocks

import com.github.bedrockecs.vanilla.data.items.ItemID

@JvmInline
value class BlockID(val value: Int) {
    fun toItemID(): ItemID {
        return if (value in 0..255) {
            ItemID(value)
        } else {
            ItemID(255 - value)
        }
    }
}
