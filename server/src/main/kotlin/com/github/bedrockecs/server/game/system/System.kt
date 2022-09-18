package com.github.bedrockecs.server.game.system

/**
 * represents a system in the ECS architecture
 */
interface System {
    /**
     * the order of tick() is called
     */
    val tickOrder: Int

    /**
     * the tick function, called every tick
     */
    fun tick()
}
