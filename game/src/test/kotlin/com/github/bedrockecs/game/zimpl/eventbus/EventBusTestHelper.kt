package com.github.bedrockecs.game.zimpl.eventbus

import com.github.bedrockecs.game.eventbus.Event
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.eventbus.Subscription
import org.mockito.kotlin.mock

open class TestReceiver<T> {
    open fun invoke(event: T) {
        // no-op
    }
}

inline fun <reified T : Event> EventBus.testListener(
    name: String = "test",
    dispatchToken: Any? = null,
    order: Int = 0
): Pair<TestReceiver<T>, Subscription> {
    val receiver = mock<TestReceiver<T>>()
    val sub = listensFor(EventBus.ListenConfig(name, T::class.java, dispatchToken, order)) { receiver.invoke(it) }
    return receiver to sub
}
