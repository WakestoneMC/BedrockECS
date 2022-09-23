package com.github.bedrockecs.server.filesystem.zimpl

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import kotlin.io.path.createDirectory
import kotlin.io.path.notExists

@Component
class ServerFileSystemInitializer(private val impl: ServerFileSystemImpl) {
    @PostConstruct
    fun initialize() {
        if (impl.pluginDir.notExists()) {
            impl.pluginDir.createDirectory()
        }
        if (impl.saveDir.notExists()) {
            impl.saveDir.createDirectory()
        }
    }
}
