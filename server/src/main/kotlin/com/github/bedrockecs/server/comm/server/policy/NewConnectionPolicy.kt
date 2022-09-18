package com.github.bedrockecs.server.comm.server.policy

/**
 * tell the network subsystem how to treat the new connections
 */
sealed class NewConnectionPolicy {
    /**
     * puts the connections on hold, will continue connecting after the policy changes to something else
     */
    class OnHold : NewConnectionPolicy()

    /**
     * accept new connections
     */
    class Accept : NewConnectionPolicy()

    /**
     * reject new connections
     */
    class Reject(val reason: String? = null, val hideReason: Boolean = false) : NewConnectionPolicy()

    /**
     * transfer new connections to another server
     */
    class Transfer(val host: String, val port: Int) : NewConnectionPolicy()
}
