package com.github.bedrockecs.server.game.system

/**
 * all important tick orders for structuring the order of ticking
 */
object CommonTickOrders {
    /**
     * at this priority the player inputs are made aware to the game server
     */
    val NETWORK_INPUT = Int.MIN_VALUE

    /**
     * at this priority we process all the interactions from the player
     */
    val PLAYER_INTERACTION = Int.MIN_VALUE + 5_000

    /**
     * at this priority we handle chunk loading/unloading
     */
    val CHUNK_LOADING = Int.MIN_VALUE + 10_000

    /**
     * game logic are suggested to start from this priority
     */
    val GAME_LOGIC = 0

    /**
     * at this priority we send packets to players / write things to storage
     */
    val NETWORK_STORAGE_OUTPUT = Int.MAX_VALUE
}
