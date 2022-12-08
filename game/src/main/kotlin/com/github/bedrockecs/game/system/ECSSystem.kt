package com.github.bedrockecs.game.system

/**
 * represents a system in the ECS architecture
 */
interface ECSSystem {
    /**
     * the order of tick() is called
     */
    val tickOrder: Int

    /**
     * the tick function, called every tick
     */
    fun tick()
}
