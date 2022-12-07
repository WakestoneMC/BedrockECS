package com.github.bedrockecs.comm.zimpl.serial

import com.github.bedrockecs.comm.serial.ItemNetworkSerializer
import com.github.bedrockecs.comm.zimpl.log
import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.invitem.InvSlotRef
import com.github.bedrockecs.game.db.invitem.data.ItemComponent
import com.nukkitx.protocol.bedrock.data.inventory.ItemData
import org.springframework.stereotype.Component

@Component
class DispatchingItemNetworkSerializer(serializers: Collection<ItemNetworkSerializer>) {

    private val serializers: List<ItemNetworkSerializer> = serializers.sortedBy { it.order }

    fun serialize(ref: InvSlotRef?, components: ComponentMap<ItemComponent>): ItemData {
        for (s in serializers) {
            val ret = s.serialize(components)
            if (ret != null) {
                return ret
            }
        }
        log.error(
            "error trying to serialize item, all serializers rejected, returning empty! [ref={}, item={}]",
            ref,
            components
        )
        return ItemData.AIR
    }
}
