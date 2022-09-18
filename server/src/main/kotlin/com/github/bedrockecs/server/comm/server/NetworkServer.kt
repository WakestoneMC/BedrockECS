package com.github.bedrockecs.server.comm.server

import com.github.bedrockecs.server.comm.server.policy.ExistingConnectionPolicy
import com.github.bedrockecs.server.comm.server.policy.NewConnectionPolicy
import java.util.UUID
import java.util.concurrent.CompletableFuture

/**
 * deals with the lowest-level packet IO:
 * * provides and manages [NetworkConnection] and "framework" for connection handling
 * * policy system for atomic control of existing and new connections
 *
 * ## Coroutine Scope
 * all coroutines coming from this subsystem are using the network thread pool, don't do CPU-heavy stuff on it!
 *
 * ## Connection Queries
 * we will keep the collections returned constant as a "snapshot",
 * but the state of connection objects in it will continue to change!
 *
 * ## Thread Safety
 * all methods are thread-safe
 */
interface NetworkServer {

    // queries //

    /**
     * list all active connections
     */
    fun listActiveConnections(): Collection<NetworkConnection>

    /**
     * find active connection by player UUID
     */
    fun findActiveConnectionByPlayerUUID(uuid: UUID): NetworkConnection?

    // atomic connection operations //

    /**
     * atomically apply actions to new connections and all existing connections
     *  @return future representing action on existing connections are done
     */
    fun applyConnectionPolicy(
        newConn: NewConnectionPolicy,
        existingConn: ExistingConnectionPolicy = ExistingConnectionPolicy.Keep()
    ): CompletableFuture<Void>
}
