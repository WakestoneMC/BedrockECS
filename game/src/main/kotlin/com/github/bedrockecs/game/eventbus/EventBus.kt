package com.github.bedrockecs.game.eventbus

import com.github.bedrockecs.game.zimpl.eventbus.EventBusImpl

/**
 * delivers event throughout the level server
 *
 * ## Thread Safety
 * All methods, including those in [Publisher] are thread-safe, listeners should be thread-safe as well.
 * Despite every event instance is processed in order, multiple threads could be publishing events at the same time.
 */
interface EventBus {
    companion object {
        fun create(): EventBus {
            return EventBusImpl()
        }
    }

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
         * order value of the listener, listeners with lower order gets called first.
         * the order is determined among all listeners that respond to the currently sending event.
         * conceptually: listenersThatAccepts(event.getClass(), dispatchToken).sortBy{ it.config.order }.map({it(event)})
         */
        val order: Int = 0
    )

    fun <T : Event> listensFor(config: ListenConfig<T>, listener: (T) -> Unit): Subscription
}
