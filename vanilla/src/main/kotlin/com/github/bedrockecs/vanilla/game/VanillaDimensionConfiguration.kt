package com.github.bedrockecs.vanilla.game

import com.github.bedrockecs.game.ext.DimensionConfiguration
import com.github.bedrockecs.vanilla.game.command.system.TestCommandSystem
import com.github.bedrockecs.vanilla.game.pinteract.system.PlayerInteractionSystem
import com.github.bedrockecs.vanilla.game.player.system.PlayerConnectDisconnectSystem
import org.springframework.context.annotation.ComponentScan

@DimensionConfiguration
@ComponentScan(
    basePackageClasses = [
        TestCommandSystem::class,
        PlayerInteractionSystem::class,
        PlayerConnectDisconnectSystem::class
    ]
)
class VanillaDimensionConfiguration
