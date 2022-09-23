package com.github.bedrockecs.vanilla.comm

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [VanillaCommConfiguration::class])
class VanillaCommConfiguration
