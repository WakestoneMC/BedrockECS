package com.github.bedrockecs.server.comm.zimpl

import com.github.bedrockecs.server.comm.zimpl.game.NetworkActionUpdateSystem
import com.github.bedrockecs.server.game.ext.GameConfiguration
import org.springframework.context.annotation.ComponentScan

@GameConfiguration
@ComponentScan(basePackageClasses = [NetworkActionUpdateSystem::class])
class CommGameConfiguration
