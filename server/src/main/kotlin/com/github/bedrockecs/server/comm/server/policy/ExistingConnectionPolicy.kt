package com.github.bedrockecs.server.comm.server.policy

/**
 * tell the network subsystem how to treat the connected connections
 */
sealed class ExistingConnectionPolicy {
    /**
     * keep them
     */
    class Keep : ExistingConnectionPolicy()

    /**
     * disconnect them
     */
    class Disconnect(val reason: String? = null, val hideReason: Boolean = false) : ExistingConnectionPolicy()

    /**
     * transfer to target server
     */
    class Transfer(val host: String, val port: Int) : ExistingConnectionPolicy()
}
