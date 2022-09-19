package com.github.bedrockecs.server.game.chunkloading.zimpl

import com.github.bedrockecs.server.game.chunkloading.zimpl.system.ChunkLoadingSystem
import com.github.bedrockecs.server.game.ext.GameConfiguration
import org.springframework.context.annotation.ComponentScan

@GameConfiguration
@ComponentScan(basePackageClasses = [ChunkLoadingSystem::class])
class ChunkLoadingGameConfiguration
