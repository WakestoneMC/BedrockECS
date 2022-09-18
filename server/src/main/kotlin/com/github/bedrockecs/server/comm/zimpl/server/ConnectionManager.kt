package com.github.bedrockecs.server.comm.zimpl.server

import com.github.bedrockecs.server.comm.server.ConnectionClosedException
import com.github.bedrockecs.server.comm.server.ConnectionState
import com.github.bedrockecs.server.comm.server.NetworkConnection.Latency
import com.github.bedrockecs.server.comm.server.policy.ExistingConnectionPolicy
import com.github.bedrockecs.server.comm.server.policy.NewConnectionPolicy
import com.github.bedrockecs.server.comm.zimpl.log
import com.github.bedrockecs.server.comm.zimpl.withConnectionDebugLog
import com.nukkitx.protocol.bedrock.packet.TransferPacket
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * helps [NetworkServerImpl] with managing network connections,
 * such as keeping an up-to-date map and applying connection policies
 * TODO: make everything inside more concurrent
 */
class ConnectionManager(
    initialPolicy: NewConnectionPolicy
) {

    private val lock: Lock = ReentrantLock()
    private val policyChanged: Condition = lock.newCondition()
    private var newPolicy: NewConnectionPolicy = initialPolicy
    private val connections: MutableSet<NetworkConnectionImpl> = HashSet()

    // queries //

    fun list(): Set<NetworkConnectionImpl> {
        lock.withLock {
            return HashSet(connections)
        }
    }

    fun listActive(): Set<NetworkConnectionImpl> {
        lock.withLock {
            return connections.filter { it.state == ConnectionState.ACTIVE }.toSet()
        }
    }

    fun findByUUID(uuid: UUID): NetworkConnectionImpl? {
        lock.withLock {
            return connections.firstOrNull { it.identifiers.playerUUID == uuid }
        }
    }

    fun findActiveByUUID(uuid: UUID): NetworkConnectionImpl? {
        lock.withLock {
            return connections
                .filter { it.state == ConnectionState.ACTIVE }
                .firstOrNull { it.identifiers.playerUUID == uuid }
        }
    }

    // group actions //

    /**
     * returns future indicating processing of existing connections
     */
    fun applyPolicy(newPolicy: NewConnectionPolicy, existingPolicy: ExistingConnectionPolicy): CompletableFuture<Void> {
        lock.withLock {
            // install new policy
            this.newPolicy = newPolicy
            this.policyChanged.signalAll()
            // apply existing
            return applyExistingPolicy(existingPolicy)
        }
    }

    // listeners //

    /**
     * returns should continue main() of connection
     */
    fun tryJoin(connection: NetworkConnectionImpl): Boolean {
        lock.withLock {
            val result = applyNewConnectionPolicy(connection)
            if (result) {
                connections.add(connection)
            }
            return result
        }
    }

    fun leave(connection: NetworkConnectionImpl) {
        lock.withLock {
            connections.remove(connection)
        }
    }

    // private: policies

    private fun applyExistingPolicy(policy: ExistingConnectionPolicy): CompletableFuture<Void> {
        when (policy) {
            is ExistingConnectionPolicy.Keep -> { return CompletableFuture.completedFuture(null) }
            is ExistingConnectionPolicy.Disconnect -> {
                lock.withLock {
                    val disconns = connections.map {
                        withConnectionDebugLog(it) {
                            log.debug(
                                "disconnecting due to policy [reason={} hideReason={}]",
                                policy.reason,
                                policy.hideReason
                            )
                        }
                        it.disconnect(policy.reason, policy.hideReason)
                    }.toTypedArray()
                    return CompletableFuture.allOf(*disconns)
                }
            }
            is ExistingConnectionPolicy.Transfer -> {
                lock.withLock {
                    val packet = TransferPacket()
                    packet.address = policy.host
                    packet.port = policy.port
                    connections.forEach {
                        withConnectionDebugLog(it) {
                            log.debug(
                                "transferring due to policy [host={} port={}]",
                                policy.host,
                                policy.port
                            )
                        }
                        try {
                            it.sendPacket(packet, Latency.IMMEDIATELY)
                        } catch (ex: ConnectionClosedException) {
                            // no-op
                        }
                    }
                    return CompletableFuture.completedFuture(null)
                }
            }
        }
    }
    private fun applyNewConnectionPolicy(connection: NetworkConnectionImpl): Boolean {
        val policy = newPolicy
        while (true) {
            if (connection.state == ConnectionState.DISCONNECTED) {
                withConnectionDebugLog(connection) {
                    log.trace("aborting due to connection closed during waiting")
                }
                return false
            }
            when (policy) {
                is NewConnectionPolicy.Accept -> return true
                is NewConnectionPolicy.Reject -> {
                    withConnectionDebugLog(connection) {
                        log.debug(
                            "disconnecting due to policy [reason={} hideReason={}]",
                            policy.reason,
                            policy.hideReason
                        )
                    }
                    connection.disconnect(policy.reason, policy.hideReason)
                    return false
                }
                is NewConnectionPolicy.Transfer -> {
                    withConnectionDebugLog(connection) {
                        log.debug("transferring due to policy [host={} port={}]", policy.host, policy.port)
                    }
                    val packet = TransferPacket()
                    packet.address = policy.host
                    packet.port = policy.port
                    try {
                        connection.sendPacket(packet, Latency.IMMEDIATELY)
                    } catch (ex: ConnectionClosedException) {
                        // no-op
                    }
                    return false
                }
                is NewConnectionPolicy.OnHold -> {
                    withConnectionDebugLog(connection) {
                        log.debug("on hold due to policy")
                    }
                    try {
                        this.policyChanged.await()
                        withConnectionDebugLog(connection) {
                            log.trace("policy changed, re-examining")
                        }
                    } catch (ex: Throwable) {
                        withConnectionDebugLog(connection) {
                            log.debug("disconnecting due to error while waiting policy changes")
                        }
                        connection.disconnect("Internal Server Error")
                        throw ex
                    }
                }
            }
        }
    }
}
