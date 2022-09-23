package com.github.bedrockecs.server.filesystem.zimpl

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import java.nio.file.Path

@Configuration
@Import(value = [ServerFileSystemInitializer::class])
class FileSystemCoreConfiguration {
    @Bean
    fun serverFileSystem(): ServerFileSystemImpl {
        return ServerFileSystemImpl(Path.of("."))
    }
}
