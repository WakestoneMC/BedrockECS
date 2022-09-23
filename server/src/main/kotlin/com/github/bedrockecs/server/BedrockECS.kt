package com.github.bedrockecs.server

import com.github.bedrockecs.server.comm.zimpl.CommCoreConfiguration
import com.github.bedrockecs.server.filesystem.zimpl.FileSystemCoreConfiguration
import com.github.bedrockecs.server.game.zimpl.GameCoreConfiguration
import com.github.bedrockecs.server.storegen.zimpl.StoreGenCoreConfiguration
import com.github.bedrockecs.server.threading.zimpl.ThreadingCoreConfiguration
import com.github.bedrockecs.server.zimpl.ChassisConfiguration
import com.github.bedrockecs.vanilla.VanillaCoreConfiguration
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
        val DEFAULT_INTRINSIC_CONFIGS: List<Class<*>> = listOf(
            ChassisConfiguration::class.java,
            ThreadingCoreConfiguration::class.java,
            CommCoreConfiguration::class.java,
            GameCoreConfiguration::class.java,
            StoreGenCoreConfiguration::class.java,
            VanillaCoreConfiguration::class.java,
            FileSystemCoreConfiguration::class.java
        )

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
