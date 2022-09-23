package com.github.bedrockecs.server.comm.zimpl.exchange

import com.github.bedrockecs.server.comm.game.action.Action
import com.github.bedrockecs.server.comm.game.action.BlockFace
import com.github.bedrockecs.server.comm.game.action.PlayerBreakBlockAction
import com.github.bedrockecs.server.comm.game.action.PlayerHotBarSelectSlotAction
import com.github.bedrockecs.server.comm.game.action.PlayerMoveAction
import com.github.bedrockecs.server.comm.game.action.PlayerUseItemAction
import com.github.bedrockecs.server.comm.game.update.DisconnectPlayerUpdate
import com.github.bedrockecs.server.comm.game.update.Update
import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.comm.zimpl.log
import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.data.inventory.TransactionType
import com.nukkitx.protocol.bedrock.packet.InventoryTransactionPacket
import com.nukkitx.protocol.bedrock.packet.MobEquipmentPacket
import com.nukkitx.protocol.bedrock.packet.MovePlayerPacket
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Component
class ActionUpdateExchange(
    private val mailbox: CommActionUpdateMailbox,
    private val connections: PlayerConnectionExchange
) {

    private data class Session(
        val lock: ReentrantLock = ReentrantLock(),
        val breaking: MutableSet<PlayerBreakBlockAction> = mutableSetOf(),
        val using: MutableSet<PlayerUseItemAction> = mutableSetOf()
    )

    private val sessions = ConcurrentHashMap<UUID, Session>()

    init {
        mailbox.addTickActionProvider(this::onCollectingAction)
    }

    // connection handling //

    suspend fun onConnection(connection: NetworkConnection) {
        sessions[connection.identifiers.playerUUID!!] = Session()
    }

    suspend fun onDisconnected(connection: NetworkConnection) {
        sessions.remove(connection.identifiers.playerUUID!!)
    }

    suspend fun onPacket(connection: NetworkConnection, packet: BedrockPacket): ProcessResult {
        return when (packet) {
            is InventoryTransactionPacket -> {
                handleInvTransaction(connection, packet)
                ProcessResult.CONSUME
            }
            is MovePlayerPacket -> {
                handleMovePlayer(connection, packet)
                ProcessResult.CONSUME
            }
            is MobEquipmentPacket -> {
                handleMobEquipment(connection, packet)
                ProcessResult.CONSUME
            }
            else -> {
                ProcessResult.CONTINUE
            }
        }
    }

    // action interpreter //

    private fun onCollectingAction(): List<Action> {
        return sessions.values
            .flatMap { session ->
                session.lock.withLock {
                    val breaking = session.breaking.toList()
                    session.breaking.clear()
                    val using = session.using.toList()
                    session.using.clear()
                    breaking + using
                }
            }
    }

    private fun handleInvTransaction(connection: NetworkConnection, packet: InventoryTransactionPacket) {
        val uuid = connection.identifiers.playerUUID!!
        val session = sessions[uuid]!!
        when (packet.transactionType) {
            TransactionType.ITEM_USE -> {
                log.info("item use packet {}", packet)
                if (packet.actionType == 0) {
                    val action = PlayerUseItemAction(
                        uuid,
                        packet.blockPosition,
                        BlockFace.parseInt(packet.blockFace)
                    )
                    session.lock.withLock {
                        session.using.add(action)
                    }
                } else if (packet.actionType == 2) {
                    val action = PlayerBreakBlockAction(uuid, packet.blockPosition, BlockFace.parseInt(packet.blockFace))
                    session.lock.withLock {
                        session.breaking.add(action)
                    }
                }
            }
            TransactionType.ITEM_USE_ON_ENTITY -> Unit
            else -> Unit
        }
    }

    private suspend fun handleMovePlayer(connection: NetworkConnection, packet: MovePlayerPacket) {
        mailbox.addAction(
            PlayerMoveAction(
                playerUUID = connection.identifiers.playerUUID!!,
                packet.position,
                packet.rotation
            )
        )
    }

    private fun handleMobEquipment(connection: NetworkConnection, packet: MobEquipmentPacket) {
        mailbox.addAction(PlayerHotBarSelectSlotAction(connection.identifiers.playerUUID!!, packet.hotbarSlot))
    }

    // update sender //

    fun sendUpdate(update: Update) {
        when (update) {
            is DisconnectPlayerUpdate -> connections.connectionForUUID(update.uuid)?.disconnect()
            else -> log.warn("ignoring unknown update [update={}]", update)
        }
    }
}
