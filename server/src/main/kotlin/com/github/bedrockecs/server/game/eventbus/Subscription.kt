package com.github.bedrockecs.server.game.eventbus

/**
 * represents a registered listener, close() to stop listening
 */
interface Subscription : AutoCloseable
