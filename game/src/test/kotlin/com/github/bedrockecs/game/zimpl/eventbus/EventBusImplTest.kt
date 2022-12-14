package com.github.bedrockecs.game.zimpl.eventbus

import com.github.bedrockecs.game.eventbus.Event
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.eventbus.publishFor
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify

/**
 * TODO: add more comprehensive tests
 */
class EventBusImplTest {

    data class TestEventA(val test: String = "test") : Event

    @Test
    fun eventBusWorks() {
        val evb = EventBus.create()
        val (receiver, _) = evb.testListener<TestEventA>("receiver")
        val sender = evb.publishFor<TestEventA>("sender")

        sender.publish(null, TestEventA())

        verify(receiver) {
            1 * { receiver.invoke(TestEventA()) }
        }
    }

    @Test
    fun eventBusUnsubscribeWorks() {
        val evb = EventBus.create()
        val (receiver, sub) = evb.testListener<TestEventA>("receiver")
        val sender = evb.publishFor<TestEventA>("sender")

        sender.publish(null, TestEventA())
        sub.close()
        sender.publish(null, TestEventA())

        verify(receiver) {
            1 * { receiver.invoke(TestEventA()) }
        }
    }

    @Test
    fun regressShouldInvokeNullListenerEvenWhenNoOneIsListeningThatToken() {
        val evb = EventBus.create()
        val (receiver, _) = evb.testListener<TestEventA>("receiver")
        val sender = evb.publishFor<TestEventA>("sender")

        sender.publish("test", TestEventA())

        verify(receiver) {
            1 * { receiver.invoke(TestEventA()) }
        }
    }
}
