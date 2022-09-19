package com.github.bedrockecs.vanilla

import com.github.bedrockecs.server.game.ext.GameConfiguration
import com.github.bedrockecs.vanilla.player.system.PlayerConnectDisconnectSystem
import org.springframework.context.annotation.ComponentScan

@GameConfiguration
@ComponentScan(
    basePackageClasses = [
        PlayerConnectDisconnectSystem::class
    ]
)
class VanillaGameConfiguration
