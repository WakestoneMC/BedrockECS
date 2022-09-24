package com.github.bedrockecs.sample.game

import com.github.bedrockecs.sample.game.system.SampleCommandSystem
import com.github.bedrockecs.server.game.ext.GameConfiguration
import org.springframework.context.annotation.Import

@GameConfiguration
@Import(SampleCommandSystem::class)
class SampleGameConfiguration
