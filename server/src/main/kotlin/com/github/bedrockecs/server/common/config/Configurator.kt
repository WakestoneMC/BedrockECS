package com.github.bedrockecs.server.common.config

/**
 * configuration hook for configuring things
 */
interface Configurator<T> {
    fun configure(obj: T)
}
