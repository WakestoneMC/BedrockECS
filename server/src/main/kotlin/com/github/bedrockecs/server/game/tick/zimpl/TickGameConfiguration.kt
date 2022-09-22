package com.github.bedrockecs.server.game.tick.zimpl

import com.github.bedrockecs.server.game.ext.GameConfiguration
import com.github.bedrockecs.server.game.tick.zimpl.system.TickSystem
import org.springframework.context.annotation.Import

@GameConfiguration
@Import(TickSystem::class)
class TickGameConfiguration
