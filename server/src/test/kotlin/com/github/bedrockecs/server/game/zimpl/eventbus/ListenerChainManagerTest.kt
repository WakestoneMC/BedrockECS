package com.github.bedrockecs.server.game.zimpl.eventbus

import com.github.bedrockecs.server.game.eventbus.Event
import com.github.bedrockecs.server.game.zimpl.eventbus.ListenerChainManager.Listener
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class ListenerChainManagerTest {
    data class TestEventA(val test: String = "test") : Event

    data class TestEventB(val test2: String = "test2") : Event

    private inline fun <reified T : Event> addTestListener(
        manager: ListenerChainManager,
        dispatchToken: Any? = null,
        order: Int = 0
    ): Triple<(T) -> Unit, Listener<T>, AutoCloseable> {
        val receiver = mock<(T) -> Unit>()
        val listener = Listener(dispatchToken, order, receiver)
        val closable = manager.addListener(T::class.java, listener)
        return Triple(receiver, listener, closable)
    }

    @Test
    fun buildChainWorks() {
        val manager = ListenerChainManager()
        val (_, listener) = addTestListener<TestEventA>(manager)

        val ret = manager.buildChain(TestEventA::class.java)

        assertEquals(ret.publishEventType, TestEventA::class.java)
        assertEquals(ret.createdAtVersion, 1)
        assertEquals(ret.tokenListenerMap, mapOf(null to listOf(listener)))
    }

    @Test
    fun buildChainHandlesParentClass() {
        val manager = ListenerChainManager()
        val (_, listener) = addTestListener<TestEventA>(manager)
        val (_, listener2) = addTestListener<Event>(manager)

        val ret = manager.buildChain(TestEventA::class.java)

        assertEquals(ret.publishEventType, TestEventA::class.java)
        assertEquals(ret.createdAtVersion, 2)
        assertEquals(ret.tokenListenerMap, mapOf(null to listOf(listener, listener2)))
    }

    @Test
    fun buildChainHandlesOrder() {
        val manager = ListenerChainManager()
        val (_, listener2) = addTestListener<TestEventA>(manager, order = 1)
        val (_, listener) = addTestListener<TestEventA>(manager, order = 0)

        val ret = manager.buildChain(TestEventA::class.java)

        assertEquals(ret.publishEventType, TestEventA::class.java)
        assertEquals(ret.createdAtVersion, 2)
        assertEquals(ret.tokenListenerMap, mapOf(null to listOf(listener, listener2)))
    }

    @Test
    fun buildChainHandlesMixedDispatcher() {
        val manager = ListenerChainManager()
        val (_, listener2) = addTestListener<TestEventA>(manager, dispatchToken = "test", order = 1)
        val (_, listener) = addTestListener<TestEventA>(manager, dispatchToken = null)

        val ret = manager.buildChain(TestEventA::class.java)

        assertEquals(ret.publishEventType, TestEventA::class.java)
        assertEquals(ret.createdAtVersion, 2)
        assertEquals(
            ret.tokenListenerMap,
            mapOf(
                "test" to listOf(listener, listener2),
                null to listOf(listener)
            )
        )
    }

    @Test
    fun updateChainReturnsNewChainIfUpdated() {
        val manager = ListenerChainManager()
        val (_, listener) = addTestListener<TestEventA>(manager)
        val oldChain = manager.buildChain(TestEventA::class.java)
        val (_, listener2) = addTestListener<TestEventA>(manager, order = 1)

        val ret = manager.updateChain(oldChain)

        assertNotEquals(oldChain, ret)
        assertEquals(ret.publishEventType, TestEventA::class.java)
        assertEquals(ret.createdAtVersion, 2)
        assertEquals(ret.tokenListenerMap, mapOf(null to listOf(listener, listener2)))
    }

    @Test
    fun updateChainKeepsOldIfNothingChanged() {
        val manager = ListenerChainManager()
        val (_, _) = addTestListener<TestEventA>(manager)
        val oldChain = manager.buildChain(TestEventA::class.java)

        val ret = manager.updateChain(oldChain)

        assertTrue(oldChain === ret)
    }

    @Test
    fun addListenerWorks() {
        val manager = ListenerChainManager()
        val receiver1 = mock<(TestEventA) -> Unit>()
        val listener1 = Listener(null, 0, receiver1)

        manager.addListener(TestEventA::class.java, listener1)

        val ret = manager.buildChain(TestEventA::class.java)
        assertEquals(ret.tokenListenerMap, mapOf(null to listOf(listener1)))
    }

    @Test
    fun removeListenerWorks() {
        val manager = ListenerChainManager()
        val (_, l1, c1) = addTestListener<TestEventA>(manager)
        val (_, l2, c2) = addTestListener<TestEventA>(manager, order = 1)

        val oldChain = manager.buildChain(TestEventA::class.java)
        assertEquals(oldChain.tokenListenerMap, mapOf(null to listOf(l1, l2)))

        c1.close()

        val newChain = manager.buildChain(TestEventA::class.java)
        assertEquals(newChain.tokenListenerMap, mapOf(null to listOf(l2)))
    }

    @Test
    fun removeListenerIsIdempotent() {
        val manager = ListenerChainManager()
        val (_, l1, c1) = addTestListener<TestEventA>(manager)
        val (_, l2, c2) = addTestListener<TestEventA>(manager, order = 1)

        val oldChain = manager.buildChain(TestEventA::class.java)
        assertEquals(oldChain.tokenListenerMap, mapOf(null to listOf(l1, l2)))

        c1.close()
        c1.close()

        val newChain = manager.buildChain(TestEventA::class.java)
        assertEquals(newChain.tokenListenerMap, mapOf(null to listOf(l2)))
    }
}
