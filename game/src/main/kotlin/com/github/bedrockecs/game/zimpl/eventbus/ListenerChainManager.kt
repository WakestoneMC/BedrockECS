package com.github.bedrockecs.game.zimpl.eventbus

import com.github.bedrockecs.game.eventbus.Event
import com.github.bedrockecs.game.eventbus.Publisher
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * manages Listener & ListenerChain
 */
class ListenerChainManager {
    /**
     * represents a registered listener
     */
    data class Listener<T : Event>(
        val dispatchToken: Any?,
        val order: Int,
        val func: (T) -> Unit
    )

    /**
     * represents all relevant listeners to a [Publisher] at a single point in time
     */
    data class ListenerChain<T : Event>(
        val publishEventType: Class<T>,
        val createdAtVersion: Long,
        /**
         * ## Invariant: order of listener
         *  listeners are ordered by their order field
         * ## Trick: null interleaving
         *  we interleave listeners with null tokens into listeners w/o null,
         *  while keeping a null key that points to all null listeners
         *  so we don't have to figure out order during publishing
         */
        val tokenListenerMap: Map<Any?, List<Listener<T>>>
    ) {
        fun invoke(dispatchToken: Any?, event: T) {
            val lookupToken = if (tokenListenerMap.contains(dispatchToken)) {
                dispatchToken
            } else {
                null
            }
            tokenListenerMap[lookupToken]?.forEach { it.func(event) }
        }
    }

    private val listenerMapLock = ReentrantReadWriteLock()

    private var listenerMapVersion: Long = 0

    /**
     * event type to listener map
     */
    private val eventListenerMap: MutableMap<Class<out Event>, MutableSet<Listener<out Event>>> = mutableMapOf()

    fun <T : Event> addListener(eventType: Class<T>, listener: Listener<T>): AutoCloseable {
        listenerMapLock.write {
            val listeners = eventListenerMap.getOrPut(eventType) { mutableSetOf() }
            listeners.add(listener)
            listenerMapVersion++
        }
        return AutoCloseable { removeListener(eventType, listener) }
    }

    private fun <T : Event> removeListener(eventType: Class<T>, listener: Listener<T>) {
        listenerMapLock.write {
            val listenerSet = eventListenerMap.get(eventType) ?: return
            val removed = listenerSet.remove(listener)
            if (removed) {
                listenerMapVersion++
            }
        }
    }

    fun <T : Event> buildChain(publishEventType: Class<T>): ListenerChain<T> {
        listenerMapLock.read {
            // pick out listeners
            val tokenListenerMap = mutableMapOf<Any?, MutableList<Listener<T>>>()
            eventListenerMap
                .filterKeys { listenerEventType -> listenerEventType.isAssignableFrom(publishEventType) }
                .flatMap { (_, listeners) -> listeners }
                .forEach { listener ->
                    val listeners = tokenListenerMap.getOrPut(listener.dispatchToken) { mutableListOf() }
                    listeners.add(listener as Listener<T>)
                }

            // performs null interleaving
            val nullTokenListeners = tokenListenerMap[null]
            if (nullTokenListeners != null) {
                tokenListenerMap
                    .filterKeys { token -> token != null }
                    .forEach { (token, listeners) ->
                        listeners.addAll(nullTokenListeners)
                    }
            }

            // sorts listeners
            tokenListenerMap.forEach { (token, listeners) ->
                listeners.sortBy { it.order }
            }

            return ListenerChain(
                publishEventType,
                listenerMapVersion,
                tokenListenerMap
            )
        }
    }

    fun <T : Event> updateChain(chain: ListenerChain<T>): ListenerChain<T> {
        listenerMapLock.read {
            if (listenerMapVersion == chain.createdAtVersion) {
                return chain
            } else {
                return buildChain(chain.publishEventType)
            }
        }
    }
}
