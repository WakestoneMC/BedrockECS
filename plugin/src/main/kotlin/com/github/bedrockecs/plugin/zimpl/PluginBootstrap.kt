package com.github.bedrockecs.plugin.zimpl

import com.github.bedrockecs.plugin.BedrockECSManifest
import org.springframework.boot.ApplicationArguments
import java.io.InputStreamReader
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.absolute
import kotlin.io.path.exists
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.listDirectoryEntries

sealed class FoundPlugins {

    abstract val manifest: BedrockECSManifest

    data class JarPlugin(
        override val manifest: BedrockECSManifest,
        val path: Path
    ) : FoundPlugins()

    data class ClassPathPlugin(
        override val manifest: BedrockECSManifest
    ) : FoundPlugins()
}

fun parseCLIArgs(args: ApplicationArguments): List<FoundPlugins.ClassPathPlugin> {
    return (args.getOptionValues("classpathPluginManifest") ?: emptyList<String>())
        .map {
            val path = Paths.get(it)
            log.debug("loading manifest for classpath plugin [path={}]", path)

            assert(path.exists()) { "path for classpath plugin manifest $path does not exists!" }

            val manifest = try {
                parseManifest(path.toFile().reader(Charsets.UTF_8))
            } catch (ex: Throwable) {
                throw IllegalArgumentException("error parsing classpath plugin manifest $path", ex)
            }

            FoundPlugins.ClassPathPlugin(manifest)
        }
}

fun scanFolder(path: Path): List<FoundPlugins.JarPlugin> {
    log.debug("scanning for plugins [folder={}]", path)
    return path.listDirectoryEntries().mapNotNull {
        if (it.isRegularFile() && it.extension == "jar") {
            log.debug("considering jar [path={}]", it)

            val manifestURL = URL("jar:${it.absolute().toUri()}!/bedrockecs.yaml")
            val manifest = try {
                manifestURL.openStream().use { s -> InputStreamReader(s).use { r -> parseManifest(r) } }
            } catch (ex: Throwable) {
                log.error("cannot parse manifest yaml inside jar, skipping [jar={}]", ex, it)
                return@mapNotNull null
            }

            log.debug("found manifest for jar [jar={}]", it)
            FoundPlugins.JarPlugin(manifest, it)
        } else {
            null
        }
    }
}

data class LoadedPlugins(
    val configurations: List<Class<*>>
)

fun loadPlugins(plugins: List<FoundPlugins>): LoadedPlugins {
    val classPathManifests = mutableListOf<BedrockECSManifest>()
    val sharedManifests = mutableListOf<BedrockECSManifest>()
    val sharedURLs = mutableListOf<URL>()
    val isolates = mutableListOf<Pair<BedrockECSManifest, URL>>()
    plugins.forEach {
        when (it) {
            is FoundPlugins.ClassPathPlugin -> when (it.manifest.classLoader) {
                BedrockECSManifest.ClassLoader.SHARED -> {
                    // no-op, we are already in classpath, even higher than the shared!
                    classPathManifests.add(it.manifest)
                }
                BedrockECSManifest.ClassLoader.ISOLATE -> {
                    log.debug("classpath plugins wanting to use isolate classloader, allowing it!")
                    classPathManifests.add(it.manifest)
                }
            }
            is FoundPlugins.JarPlugin -> when (it.manifest.classLoader) {
                BedrockECSManifest.ClassLoader.SHARED -> {
                    sharedManifests.add(it.manifest)
                    sharedURLs.add(it.path.absolute().toUri().toURL())
                }
                BedrockECSManifest.ClassLoader.ISOLATE -> {
                    isolates.add(it.manifest to it.path.absolute().toUri().toURL())
                }
            }
        }
    }

    val classPathClassLoader = Thread.currentThread().contextClassLoader
    val sharedClassLoader = URLClassLoader("becs-shared", sharedURLs.toTypedArray(), classPathClassLoader)
    val isolateManifestLoaders = isolates.map { (manifest, url) ->
        manifest to URLClassLoader("becs-isolate-${manifest.name}", arrayOf(url), sharedClassLoader)
    }

    val classPathConfigs = classPathManifests.flatMap {
        it.configurations.map { classPathClassLoader.loadClass(it) }
    }

    val sharedConfigs = sharedManifests.flatMap {
        it.configurations.map { sharedClassLoader.loadClass(it) }
    }

    val isolateConfigs = isolateManifestLoaders.flatMap { (manifest, loader) ->
        manifest.configurations.map { loader.loadClass(it) }
    }

    val configs = classPathConfigs + sharedConfigs + isolateConfigs

    log.debug("loaded configurations [plugins={}]", configs)

    return LoadedPlugins(configs)
}
