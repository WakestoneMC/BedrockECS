package com.github.bedrockecs.game.ext

import com.github.bedrockecs.common.config.Configurator
import com.github.bedrockecs.game.system.ECSSystem
import com.github.bedrockecs.game.DimensionServer
import org.springframework.context.annotation.Configuration

/**
 * represents a configuration for [DimensionServer] level application context,
 * can define [ECSSystem], [Configurator] and other beans
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Configuration
annotation class DimensionConfiguration
