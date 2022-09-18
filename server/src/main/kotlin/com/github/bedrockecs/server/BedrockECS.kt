package com.github.bedrockecs.server

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

/**
 * main entrypoint to the BedrockECS Server Software
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableWebFlux
class BedrockECS {
    companion object {
        @JvmStatic
        val DEFAULT_INTRINSIC_CONFIGS: List<Class<*>> = listOf()

        @JvmStatic
        fun run(args: Array<String>) {
            run(args, DEFAULT_INTRINSIC_CONFIGS)
        }

        @JvmStatic
        fun run(args: Array<String>, intrinsicPlugins: Collection<Class<*>>) {
            runApplication<BedrockECS>(*args) {
                sources = intrinsicPlugins.map { it.canonicalName }.toSet()
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            run(args)
        }
    }
}
