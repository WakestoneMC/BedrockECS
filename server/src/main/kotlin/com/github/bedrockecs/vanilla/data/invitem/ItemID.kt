package com.github.bedrockecs.vanilla.data.invitem

import com.github.bedrockecs.vanilla.data.world.BlockID

@JvmInline
value class ItemID(val value: Int) {
    fun toBlockID(): BlockID {
        return if (value < 0) {
            BlockID(255 - value)
        } else if (value in 0..255) {
            BlockID(value)
        } else {
            throw IllegalArgumentException("item id $this does not represent a block!")
        }
    }
}
