package com.github.bedrockecs.game.eventbus

/**
 * represents a publisher that publishes events, close() to stop publishing
 */
interface Publisher<T : Event> : AutoCloseable {
    /**
     * list all dispatch tokens for this event type
     */
    val dispatchTokens: Set<Any?>

    /**
     * publish an event
     */
    fun publish(dispatchToken: Any?, event: T)
}
