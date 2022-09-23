package com.github.bedrockecs.vanilla.game

import com.github.bedrockecs.server.game.ext.GameConfiguration
import com.github.bedrockecs.vanilla.data.VanillaBlockConfigurator
import com.github.bedrockecs.vanilla.game.player.system.PlayerConnectDisconnectSystem
import org.springframework.context.annotation.ComponentScan

@GameConfiguration
@ComponentScan(
    basePackageClasses = [
        PlayerConnectDisconnectSystem::class,
        VanillaBlockConfigurator::class
    ]
)
class VanillaGameConfiguration
