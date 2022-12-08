package com.github.bedrockecs.game.chunkloading.zimpl

import com.github.bedrockecs.game.chunkloading.zimpl.system.ChunkLoadingSystem
import com.github.bedrockecs.game.ext.DimensionConfiguration
import org.springframework.context.annotation.Import

@DimensionConfiguration
@Import(ChunkLoadingSystem::class)
class ChunkLoadingGameConfiguration
