package com.github.bedrockecs.server

/**
 * used to report exception and performs server shutdown
 */
interface GlobalExceptionHook {
    fun report(ex: Throwable)
}
