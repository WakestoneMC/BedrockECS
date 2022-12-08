package com.github.bedrockecs.game.zimpl.db.entity.testdata

import com.github.bedrockecs.game.data.FloatBlockPosition
import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.entity.data.EntityComponent
import com.github.bedrockecs.game.db.entity.data.EntityPositionComponent
import com.github.bedrockecs.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.game.db.entity.data.CommonEntityTypes
import com.github.bedrockecs.game.db.entity.data.PlayerIdentityComponent
import com.nukkitx.math.vector.Vector3f
import java.util.UUID

object TestEntities {
    val TEST_ENTITY_A_NAME = "steve"
    val TEST_ENTITY_A_UUID = UUID.randomUUID()
    val TEST_ENTITY_A_POSITION = FloatBlockPosition(10.0f, 10.0f, 10.0f)
    val TEST_ENTITY_A_DIRECTION = Vector3f.UNIT_X
    val TEST_SERIAL_ENTITY_A_PARTS: ComponentMap<EntityComponent> = mutableMapOf(
        EntityTypeComponent::class.java to CommonEntityTypes.PLAYER,
        PlayerIdentityComponent::class.java to PlayerIdentityComponent(TEST_ENTITY_A_NAME, TEST_ENTITY_A_UUID),
        EntityPositionComponent::class.java to EntityPositionComponent(TEST_ENTITY_A_POSITION, TEST_ENTITY_A_DIRECTION),
        TestComponentA::class.java to TestComponentA()
    )
    val TEST_SERIAL_ENTITY_B_PARTS: ComponentMap<EntityComponent> = mutableMapOf(
        EntityTypeComponent::class.java to TestTypeComponents.TYPE_A,
        TestComponentB::class.java to TestComponentB()
    )
}
