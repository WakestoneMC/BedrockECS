package com.github.bedrockecs.server.game.eventbus

/**
 * the eventbus that delivers event throughout the game server
 *
 * ## Thread Safety
 * All methods, including those in [Publisher] are thread-safe, listeners should be thread-safe as well.
 *  As event could be published on many threads concurrently despite every event instance is processed in order.
 *
 */
interface EventBus {
    /**
     * config for the publisher
     */
    data class PublishConfig<T : Event>(
        /**
         * the event type to publish, must be concrete types
         */
        val eventType: Class<T>,
        /**
         * name of the publisher
         */
        val name: String
    )

    fun <T : Event> publishFor(config: PublishConfig<T>): Publisher<T>

    /**
     * config for the listener
     */
    data class ListenConfig<T : Event>(
        /**
         * name of the listener
         */
        val name: String,
        /**
         * the event type to listen to, could be parent type of many concrete types
         */
        val eventType: Class<T>,
        /**
         * dispatch token, act as filter:
         * null for no filtering
         * value for only listening to events published with same dispatch token
         */
        val dispatchToken: Any? = null,
        /**
         * order value of the listener, listeners with lower order value gets called first.
         * the order is determined among all listeners that respond to the currently sending event.
         * conceptually: listenersThatAccepts(event.getClass(), dispatchToken).sortBy{ it.config.order }.map({it(event)})
         */
        val order: Int = 0
    )

    fun <T : Event> listensFor(config: ListenConfig<T>, listener: (T) -> Unit): Subscription
}
