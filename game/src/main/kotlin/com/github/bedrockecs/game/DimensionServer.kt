package com.github.bedrockecs.game

import java.time.Duration

/**
 * runs the entire game process inside a single dimension
 */
interface DimensionServer {
    /**
     * start the server
     */
    fun start()

    /**
     * tick this dimension
     * @return the amount of time it took
     */
    fun tick(): Duration

    /**
     * stop the server
     */
    fun stop()
}
