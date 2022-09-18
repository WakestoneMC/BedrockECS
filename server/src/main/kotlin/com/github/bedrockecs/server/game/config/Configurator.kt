package com.github.bedrockecs.server.game.config

/**
 * configuration hook for configuring things
 */
interface Configurator<T> {
    fun configure(obj: T)
}
