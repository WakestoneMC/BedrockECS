package com.github.bedrockecs.comm.serial

import com.github.bedrockecs.server.game.db.common.ComponentMap
import com.github.bedrockecs.server.game.db.invitem.data.ItemComponent
import com.nukkitx.protocol.bedrock.data.inventory.ItemData

/**
 * network serializer for item
 */
interface ItemNetworkSerializer {
    /**
     * order of serializers getting called
     */
    val order: Int
        get() = 0

    /**
     * attempts to serialize it, returns null if it cannot
     */
    fun serialize(components: ComponentMap<ItemComponent>): ItemData?
}
