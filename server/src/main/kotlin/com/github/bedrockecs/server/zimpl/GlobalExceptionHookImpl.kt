package com.github.bedrockecs.server.zimpl

import com.github.bedrockecs.server.GlobalExceptionHook
import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

class GlobalExceptionHookImpl(
    private val context: ConfigurableApplicationContext
) : GlobalExceptionHook {
    override fun report(ex: Throwable) {
        log.error("internal error encountered, shutting down!", ex)
        SpringApplication.exit(context, ExitCodeGenerator { -1 })
    }
}
