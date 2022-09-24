package com.github.bedrockecs.server.filesystem.zimpl

import kotlin.io.path.createDirectory
import kotlin.io.path.notExists

fun initializeFileSystem(impl: ServerFileSystemImpl) {
    if (impl.pluginDir.notExists()) {
        impl.pluginDir.createDirectory()
    }
    if (impl.saveDir.notExists()) {
        impl.saveDir.createDirectory()
    }
}
