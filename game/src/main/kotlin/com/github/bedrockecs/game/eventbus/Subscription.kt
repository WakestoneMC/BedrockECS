package com.github.bedrockecs.game.eventbus

/**
 * represents a registered listener, close() to stop listening
 */
interface Subscription : AutoCloseable
