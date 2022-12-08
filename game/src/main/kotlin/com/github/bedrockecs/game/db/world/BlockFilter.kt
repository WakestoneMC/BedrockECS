package com.github.bedrockecs.game.db.world

/**
 * filter classes, used to limit the range of scan operations
 */
sealed class BlockFilter {
    /**
     * no-op
     */
    object None : BlockFilter()
    // TODO: support AND, HasAnyOfType, HasAnyOfState, HasAllOfComponent
}
