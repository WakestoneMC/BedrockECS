package com.github.bedrockecs.game.system

/**
 * all important tick orders for structuring the order of ticking
 */
object CommonTickOrders {
    /**
     * at this priority we handle pre tick operations
     */
    val PRE_TICK = Int.MIN_VALUE

    /**
     * at this priority the player inputs are made aware to the server
     */
    val INPUT = Int.MIN_VALUE + 10_000

    /**
     * at this priority we process all the interactions, from players or other entities
     */
    val INTERACTION = Int.MIN_VALUE + 15_000

    /**
     * at this priority we handle chunk loading/unloading
     */
    val CHUNK_LOADING = Int.MIN_VALUE + 20_000

    /**
     * game logic are suggested to start from this priority
     */
    val GAME_LOGIC = 0

    /**
     * at this priority we send packets to players
     */
    val OUTPUT = Int.MAX_VALUE - 10_000

    /**
     * at this priority we handle post tick operations, such as writing things to storage
     */
    val POST_TICK = Int.MAX_VALUE - 10_000
}
