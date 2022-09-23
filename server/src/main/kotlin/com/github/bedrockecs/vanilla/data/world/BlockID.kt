package com.github.bedrockecs.vanilla.data.world

import com.github.bedrockecs.vanilla.data.invitem.ItemID

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
