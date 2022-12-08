package com.github.bedrockecs.game.db.common

/**
 * type to instance map
 */
typealias ComponentMap<T> = Map<Class<out T>, T>

/**
 * mutable variant of [ComponentMap]
 */
typealias MutableComponentMap<T> = MutableMap<Class<out T>, T>
