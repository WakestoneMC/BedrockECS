package com.github.bedrockecs.server.comm.zimpl

import com.github.bedrockecs.server.comm.server.ConnectionIdentifiers
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.comm.zimpl.server.NetworkConnectionImpl
import com.nukkitx.protocol.bedrock.BedrockServerSession
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import java.net.SocketAddress

val log: Logger = LoggerFactory.getLogger("comm")

fun withConnection(conn: NetworkConnection, func: () -> Unit) {
    withConnection(conn.identifiers, func)
}

fun withConnectionDebugLog(conn: NetworkConnection, func: () -> Unit) {
    if (log.isDebugEnabled) {
        withConnection(conn.identifiers, func)
    }
}

fun withConnection(conn: NetworkConnectionImpl, func: () -> Unit) {
    withConnection(conn.session, conn.identifiers, func)
}

fun withConnectionDebugLog(conn: NetworkConnectionImpl, func: () -> Unit) {
    if (log.isDebugEnabled) {
        withConnection(conn.session, conn.identifiers, func)
    }
}

fun withConnection(
    session: BedrockServerSession,
    identifiers: ConnectionIdentifiers,
    func: () -> Unit
) {
    MDC.put("conn", Integer.toHexString(System.identityHashCode(session)))
    try {
        withConnection(identifiers, func)
    } finally {
        MDC.remove("conn")
    }
}

fun withConnection(
    identifiers: ConnectionIdentifiers,
    func: () -> Unit
) {
    MDC.put("name", identifiers.displayName ?: "null")
    try {
        func()
    } finally {
        MDC.remove("name")
    }
}

fun withSource(src: SocketAddress, realSrc: SocketAddress? = null, func: () -> Unit) {
    MDC.put("src", src.toString())
    if (realSrc != null) {
        MDC.put("realSrc", realSrc.toString())
    }
    try {
        func()
    } finally {
        MDC.remove("src")
        if (realSrc != null) {
            MDC.remove("realSrc")
        }
    }
}

fun withSourceDebugLog(src: SocketAddress, realSrc: SocketAddress? = null, func: () -> Unit) {
    if (log.isDebugEnabled) {
        withSource(src, realSrc, func)
    }
}
