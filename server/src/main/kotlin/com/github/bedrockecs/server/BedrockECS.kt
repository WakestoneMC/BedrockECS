package com.github.bedrockecs.server

import com.github.bedrockecs.server.comm.zimpl.CommCoreConfiguration
import com.github.bedrockecs.server.filesystem.ServerFileSystem
import com.github.bedrockecs.server.filesystem.zimpl.ServerFileSystemImpl
import com.github.bedrockecs.server.filesystem.zimpl.initializeFileSystem
import com.github.bedrockecs.server.game.zimpl.GameCoreConfiguration
import com.github.bedrockecs.server.plugin.zimpl.loadPlugins
import com.github.bedrockecs.server.plugin.zimpl.parseCLIArgs
import com.github.bedrockecs.server.plugin.zimpl.scanFolder
import com.github.bedrockecs.server.storegen.zimpl.StoreGenCoreConfiguration
import com.github.bedrockecs.server.threading.zimpl.ThreadingCoreConfiguration
import com.github.bedrockecs.server.zimpl.ChassisConfiguration
import com.github.bedrockecs.vanilla.VanillaCoreConfiguration
import org.springframework.boot.DefaultApplicationArguments
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext
import org.springframework.web.reactive.config.EnableWebFlux
import java.nio.file.Path
import kotlin.io.path.absolute

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
            VanillaCoreConfiguration::class.java
        )

        @JvmStatic
        fun run(
            args: Array<String>,
            intrinsicPlugins: Collection<Class<*>> = DEFAULT_INTRINSIC_CONFIGS,
            filesystem: ServerFileSystem? = null
        ) {
            val parsedArgs = DefaultApplicationArguments(*args)

            val finalFileSystem = if (filesystem != null) {
                filesystem
            } else {
                val impl = ServerFileSystemImpl(Path.of(".").absolute().normalize())
                initializeFileSystem(impl)
                impl
            }

            val foundPlugins = scanFolder(finalFileSystem.pluginDir) + parseCLIArgs(parsedArgs)
            val loaded = loadPlugins(foundPlugins)

            runApplication<BedrockECS>(*args) {
                sources = intrinsicPlugins.map { it.canonicalName }.toSet()
                addInitializers({ ctx ->
                    ctx.beanFactory.registerSingleton("serverFileSystem", finalFileSystem)
                    ctx as AnnotationConfigReactiveWebServerApplicationContext
                    if (loaded.configurations.isNotEmpty()) {
                        ctx.register(*loaded.configurations.toTypedArray())
                    }
                })
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            run(args)
        }
    }
}
