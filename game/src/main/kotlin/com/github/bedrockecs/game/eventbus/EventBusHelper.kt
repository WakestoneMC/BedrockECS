/**
 * simplified shorthands for [EventBus]
 */
package com.github.bedrockecs.game.eventbus

inline fun <reified T : Event> EventBus.publishFor(name: String): Publisher<T> {
    return publishFor(
        EventBus.PublishConfig(
            eventType = T::class.java,
            name = name
        )
    )
}

inline fun <reified T : Event> EventBus.listensFor(name: String, noinline listener: (T) -> Unit): Subscription {
    return listensFor(name, null, listener)
}

inline fun <reified T : Event> EventBus.listensFor(
    name: String,
    dispatchToken: Any?,
    noinline listener: (T) -> Unit
): Subscription {
    return listensFor(
        EventBus.ListenConfig(name = name, dispatchToken = dispatchToken, eventType = T::class.java),
        listener
    )
}
