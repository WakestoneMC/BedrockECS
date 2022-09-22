package com.github.bedrockecs.server.game.zimpl.eventbus

import com.github.bedrockecs.server.game.eventbus.Event
import com.github.bedrockecs.server.game.eventbus.publishFor
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify

/**
 * TODO: add more comprehensive tests
 */
class EventBusImplTest {

    data class TestEventA(val test: String = "test") : Event

    @Test
    fun eventBusWorks() {
        val evb = EventBusImpl()
        val (receiver, _) = evb.testListener<TestEventA>("receiver")
        val sender = evb.publishFor<TestEventA>("sender")

        sender.publish(null, TestEventA())

        verify(receiver) {
            1 * { receiver.invoke(TestEventA()) }
        }
    }

    @Test
    fun eventBusUnsubscribeWorks() {
        val evb = EventBusImpl()
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
        val evb = EventBusImpl()
        val (receiver, sub) = evb.testListener<TestEventA>("receiver")
        val sender = evb.publishFor<TestEventA>("sender")

        sender.publish("test", TestEventA())

        verify(receiver) {
            1 * { receiver.invoke(TestEventA()) }
        }
    }
}
