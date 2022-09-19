package com.github.bedrockecs.server.zimpl

import com.github.bedrockecs.server.GlobalExceptionHook
import org.springframework.context.ConfigurableApplicationContext

class GlobalExceptionHookImpl(
    private val context: ConfigurableApplicationContext
) : GlobalExceptionHook {
    override fun report(ex: Throwable) {
        log.error("internal error encountered, shutting down!", ex)
        context.close()
    }
}
