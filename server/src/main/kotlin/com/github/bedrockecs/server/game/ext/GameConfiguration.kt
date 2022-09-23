package com.github.bedrockecs.server.game.ext

import com.github.bedrockecs.server.common.config.Configurator
import com.github.bedrockecs.server.game.system.System
import org.springframework.context.annotation.Configuration

/**
 * represents a configuration for game-level application context,
 * can define [System], [Configurator] and other beans
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Configuration
annotation class GameConfiguration
