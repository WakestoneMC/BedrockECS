package com.github.bedrockecs.server.game.zimpl.eventbus

import com.github.bedrockecs.server.game.eventbus.Event
import com.github.bedrockecs.server.game.eventbus.EventBus
import com.github.bedrockecs.server.game.eventbus.Publisher
import com.github.bedrockecs.server.game.eventbus.Subscription
import com.github.bedrockecs.server.game.zimpl.eventbus.ListenerChainManager.Listener
import com.github.bedrockecs.server.game.zimpl.eventbus.ListenerChainManager.ListenerChain
import java.util.concurrent.atomic.AtomicReference

/**
 * implementation of [EventBus]
 */
class EventBusImpl : EventBus {
    class PublisherImpl<T : Event>(
        chain: ListenerChain<T>,
        private val manager: ListenerChainManager
    ) : Publisher<T> {

        @Volatile private var chain: AtomicReference<ListenerChain<T>> = AtomicReference(chain)

        override val dispatchTokens: Set<Any?>
            get() {
                val chain = this.chain.updateAndGet { manager.updateChain(it) }
                return chain.tokenListenerMap.keys
            }

        override fun publish(dispatchToken: Any?, event: T) {
            val chain = this.chain.updateAndGet { manager.updateChain(it) }
            return chain.invoke(dispatchToken, event)
        }

        override fun close() {
            // no-op, just discard
        }
    }

    private val manager: ListenerChainManager = ListenerChainManager()

    override fun <T : Event> publishFor(config: EventBus.PublishConfig<T>): Publisher<T> {
        return PublisherImpl(manager.buildChain(config.eventType), manager)
    }

    override fun <T : Event> listensFor(config: EventBus.ListenConfig<T>, listener: (T) -> Unit): Subscription {
        val managerClose = manager.addListener(
            config.eventType,
            Listener(config.dispatchToken, config.order, listener)
        )
        return object : Subscription {
            override fun close() {
                managerClose.close()
            }
        }
    }
}
