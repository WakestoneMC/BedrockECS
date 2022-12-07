package com.github.bedrockecs.game.zimpl.db.invitem

import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.invitem.InvItemDB

/**
 * unstable internal API for [InvItemDB]
 */
interface InvItemDBInternal : InvItemDB {
    // region eventing

    /**
     * when an entity is getting destroyed
     */
    fun onEntityDestroying(eid: EntityID)

    // endregion
}
