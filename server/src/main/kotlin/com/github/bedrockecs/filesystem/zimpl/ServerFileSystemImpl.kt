package com.github.bedrockecs.filesystem.zimpl

import com.github.bedrockecs.filesystem.ServerFileSystem
import java.nio.file.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.notExists

class ServerFileSystemImpl(rootDir: Path) : ServerFileSystem {

    override val saveDir: Path = rootDir.resolve("world")

    override val pluginDir: Path = rootDir.resolve("plugins")

    override fun dataDirForPlugin(name: String): Path {
        val path = pluginDir.resolve(name)
        if (path.notExists()) {
            path.createDirectory()
        }
        return path
    }
}
