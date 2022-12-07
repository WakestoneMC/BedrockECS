package com.github.bedrockecs.comm.zimpl

import com.github.bedrockecs.comm.zimpl.game.NetworkActionUpdateSystem
import com.github.bedrockecs.game.ext.DimensionConfiguration
import org.springframework.context.annotation.ComponentScan

@DimensionConfiguration
@ComponentScan(basePackageClasses = [NetworkActionUpdateSystem::class])
class CommDimensionConfiguration
