package com.github.bedrockecs.server.threading

import org.springframework.beans.factory.annotation.Qualifier

/**
 * qualifier for boss [EventLoopGroup] for netty networking
 * ## DI Container
 * provided in core DI container
 */
@Qualifier
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class BossEventLoopGroup()
