package com.github.bedrockecs.server.game.zimpl.eventbus

import com.github.bedrockecs.server.game.eventbus.Event
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.eventbus.Publisher
import com.github.bedrockecs.server.game.eventbus.Subscription
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class NaiveEventBusImpl : EventBus {
    data class Listener(
        val config: EventBus.ListenConfig<*>,
        val listener: (Event) -> Unit
    )

    private val listenerMapLock: ReentrantLock = ReentrantLock()
    private val listenerMap: MutableMap<Class<out Event>, MutableMap<Any?, MutableList<Listener>>> = mutableMapOf()

    override fun <T : Event> publishFor(config: EventBus.PublishConfig<T>): Publisher<T> {
        return object : Publisher<T> {
            override val dispatchTokens: Set<Any?>
                get() {
                    return listenerMapLock.withLock {
                        listenerMap
                            .filterKeys { it.isAssignableFrom(config.eventType) }
                            .flatMap { it.value.keys }
                            .toSet()
                    }
                }

            override fun close() {
                // no-op
            }

            override fun publish(dispatchToken: Any?, event: T) {
                val listeners: List<Listener>
                listenerMapLock.withLock {
                    listeners = listenerMap
                        .filterKeys { it.isAssignableFrom(config.eventType) }
                        .flatMap { it.value.filter { it.key == null || it.key == dispatchToken }.values }
                        .flatten()
                        .sortedBy { it.config.order }
                }
                listeners.forEach { it.listener(event) }
            }
        }
    }

    override fun <T : Event> listensFor(config: EventBus.ListenConfig<T>, listener: (T) -> Unit): Subscription {
        val internalListener = Listener(config) { listener(it as T) }

        val subscription = object : Subscription {
            override fun close() {
                listenerMapLock.withLock {
                    listenerMap[config.eventType]?.get(config.dispatchToken)?.remove(internalListener)
                }
            }
        }

        listenerMapLock.withLock {
            val list = listenerMap
                .getOrPut(config.eventType, ::mutableMapOf)
                .getOrPut(config.dispatchToken, ::mutableListOf)
            list.add(internalListener)
        }

        return subscription
    }
}
