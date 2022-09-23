package com.github.bedrockecs.server.filesystem

import java.nio.file.Path

/**
 * interface for accessing file paths
 */
interface ServerFileSystem {
    /**
     * world save directory
     */
    val saveDir: Path

    /**
     * plugin directory
     */
    val pluginDir: Path

    /**
     * path to plugin data folder
     */
    fun dataDirForPlugin(name: String): Path
}
