package com.github.bedrockecs.game.tick.zimpl

import com.github.bedrockecs.game.ext.DimensionConfiguration
import com.github.bedrockecs.game.tick.zimpl.system.TickSystem
import org.springframework.context.annotation.Import

@DimensionConfiguration
@Import(TickSystem::class)
class TickDimensionConfiguration
