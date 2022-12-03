package com.github.bedrockecs.common.config

/**
 * configuration hook for configuring things
 */
interface Configurator<T> {
    fun configure(obj: T)
}
