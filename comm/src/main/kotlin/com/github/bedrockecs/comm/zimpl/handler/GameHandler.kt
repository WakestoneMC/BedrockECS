package com.github.bedrockecs.comm.zimpl.handler

import com.github.bedrockecs.comm.server.NetworkConnection
import com.github.bedrockecs.comm.zimpl.exchange.ActionUpdateExchange
import com.github.bedrockecs.comm.zimpl.exchange.CommandChatExchange
import com.github.bedrockecs.comm.zimpl.exchange.GameWorldExchange
import com.github.bedrockecs.comm.zimpl.exchange.PlayerConnectionExchange
import com.github.bedrockecs.comm.zimpl.exchange.ProcessResult
import com.github.bedrockecs.game.data.FloatBlockPosition
import com.github.bedrockecs.game.db.entity.EntityID
import com.nukkitx.math.vector.Vector2f
import com.nukkitx.math.vector.Vector3f
import com.nukkitx.math.vector.Vector3i
import com.nukkitx.nbt.NbtMap
import com.nukkitx.nbt.NbtUtils
import com.nukkitx.protocol.bedrock.data.AuthoritativeMovementMode
import com.nukkitx.protocol.bedrock.data.EduSharedUriResource
import com.nukkitx.protocol.bedrock.data.GamePublishSetting
import com.nukkitx.protocol.bedrock.data.GameRuleData
import com.nukkitx.protocol.bedrock.data.GameType
import com.nukkitx.protocol.bedrock.data.PlayerPermission
import com.nukkitx.protocol.bedrock.data.SpawnBiomeType
import com.nukkitx.protocol.bedrock.data.SyncedPlayerMovementSettings
import com.nukkitx.protocol.bedrock.packet.AvailableEntityIdentifiersPacket
import com.nukkitx.protocol.bedrock.packet.BiomeDefinitionListPacket
import com.nukkitx.protocol.bedrock.packet.NetworkSettingsPacket
import com.nukkitx.protocol.bedrock.packet.PlayStatusPacket
import com.nukkitx.protocol.bedrock.packet.SetCommandsEnabledPacket
import com.nukkitx.protocol.bedrock.packet.StartGamePacket
import org.springframework.stereotype.Component

/**
 * deals with everything about game protocol, spawning, interaction, world update......
 */
@Component
class GameHandler(
    private val worldExchange: GameWorldExchange,
    private val connectionExchange: PlayerConnectionExchange,
    private val actionUpdateExchange: ActionUpdateExchange,
    private val chatExchange: CommandChatExchange
) {
    suspend fun handle(conn: NetworkConnection) {
        connectionExchange.handleConnection(conn) { spawnedPlayer ->
            // network setting & client cache
            conn.sendPacket(NetworkSettingsPacket().apply { compressionThreshold = 1 })
            // receive ClientCacheStatusPacket TODO: deals with ClientCacheStatusPacket

            // metadata initialization //
            conn.sendPacket(
                computeStartGamePacket(
                    spawnedPlayer.currentTick,
                    spawnedPlayer.eid,
                    spawnedPlayer.pos.pos,
                    spawnedPlayer.pos.direction
                )
            )
            // connection.sendPacket(computeItemComponentPacket()) TODO: send item component constants
            conn.sendPacket(SetCommandsEnabledPacket().apply { isCommandsEnabled = true })
            // AvailableCommandsPacket TODO: send available commands
            // AdventureSettingsPacket TODO: specify adventure settings
            conn.sendPacket(computeBiomeDefinitionListPacket())
            conn.sendPacket(computeAvailableEntityIdentifiersPacket()) // CreativeContentPacket TODO: send creative content
            // CraftingDataPacket TODO: send crafting data content

            // game state //
            // PlayerListPacket TODO: send player list
            // SetTimePacket TODO: send time
            // PlayerFogPacket TODO: specify player fog

            // player state //
            // InventoryContentPacket got one unknown stuff

            // UpdateAttributesPacket
            // UpdateAttributesPacket
            // SetEntityDataPacket
            // SetEntityDataPacket
            // SetHealthPacket

            // execute respawn //
            // RespawnPacket
            // RespawnPacket
            // RespawnPacket

            actionUpdateExchange.onConnection(conn)
            worldExchange.onConnection(conn)
            chatExchange.onConnection(conn)
            try {
                // done //
                conn.sendPacket(PlayStatusPacket().apply { status = PlayStatusPacket.Status.PLAYER_SPAWN })

                while (true) {
                    val packet = conn.receivePacket()
                    var ret = chatExchange.onPacket(conn, packet)
                    if (ret == ProcessResult.CONSUME) {
                        continue
                    }
                    ret = actionUpdateExchange.onPacket(conn, packet)
                    if (ret == ProcessResult.CONSUME) {
                        continue
                    }
                    ret = worldExchange.onPacket(conn, packet)
                    if (ret == ProcessResult.CONSUME) {
                        continue
                    }
                }
            } finally {
                chatExchange.onDisconnected(conn)
                worldExchange.onDisconnected(conn)
                actionUpdateExchange.onDisconnected(conn)
            }
        }
    }

    private suspend fun computeStartGamePacket(
        currentTick: Long,
        playerEntityID: EntityID,
        playerPosition: FloatBlockPosition,
        playerRotation: Vector3f
    ): StartGamePacket {
        val lists = listOf(
            GameRuleData("commandblocksenabled", true),
            GameRuleData("commandblockoutput", true),
            GameRuleData("dodaylightcycle", true),
            GameRuleData("doentitydrops", true),
            GameRuleData("dofiretick", true),
            GameRuleData("doinsomnia", true),
            GameRuleData("doimmediaterespawn", false),
            GameRuleData("domobloot", true),
            GameRuleData("domobspawning", true),
            GameRuleData("dotiledrops", true),
            GameRuleData("doweathercycle", true),
            GameRuleData("drowningdamage", true),
            GameRuleData("falldamage", true),
            GameRuleData("firedamage", true),
            GameRuleData("freezedamage", true),
            GameRuleData("functioncommandlimit", 10000),
            GameRuleData("keepinventory", false),
            GameRuleData("maxcommandchainlength", 65536),
            GameRuleData("mobgriefing", true),
            GameRuleData("naturalregeneration", true),
            GameRuleData("pvp", true),
            GameRuleData("randomtickspeed", 3),
            GameRuleData("sendcommandfeedback", true),
            GameRuleData("showcoordinates", false),
            GameRuleData("showdeathmessages", true),
            GameRuleData("spawnradius", 5),
            GameRuleData("tntexplodes", true),
            GameRuleData("experimentalgameplay", false),
            GameRuleData("showtags", true)
        )

        val p1 = StartGamePacket()
        p1.gamerules.addAll(lists)
        p1.uniqueEntityId = playerEntityID.value.toLong()
        p1.runtimeEntityId = playerEntityID.value.toLong()
        p1.playerGameType = GameType.CREATIVE

        p1.playerPosition = Vector3f.from(playerPosition.x, playerPosition.y, playerPosition.z)
        p1.rotation = Vector2f.from(playerRotation.x, playerRotation.z)
        p1.seed = -1
        p1.spawnBiomeType = SpawnBiomeType.DEFAULT
        p1.customBiomeName = "plains"
        p1.dimensionId = 0
        p1.generatorId = 1
        p1.levelGameType = GameType.CREATIVE
        p1.difficulty = 1
        p1.defaultSpawn = Vector3i.from(125, 78, 130)
        p1.isAchievementsDisabled = true
        p1.dayCycleStopTime = 1
        p1.eduEditionOffers = 0
        p1.isEduFeaturesEnabled = false
        p1.educationProductionId = ""
        p1.rainLevel = 0.0f
        p1.lightningLevel = 0.0f
        p1.isPlatformLockedContentConfirmed = false
        p1.isMultiplayerGame = true
        p1.isBroadcastingToLan = true
        p1.xblBroadcastMode = GamePublishSetting.NO_MULTI_PLAY
        p1.platformBroadcastMode = GamePublishSetting.NO_MULTI_PLAY
        p1.isCommandsEnabled = true
        p1.isTexturePacksRequired = false
        p1.experiments.clear()
        p1.isExperimentsPreviouslyToggled = false
        p1.isBonusChestEnabled = false
        p1.isStartingWithMap = false
        p1.isTrustingPlayers = false
        p1.defaultPlayerPermission = PlayerPermission.MEMBER
        p1.serverChunkTickRange = 4
        p1.isBehaviorPackLocked = false
        p1.isResourcePackLocked = false
        p1.isFromLockedWorldTemplate = false
        p1.isUsingMsaGamertagsOnly = false
        p1.isFromWorldTemplate = false
        p1.isWorldTemplateOptionLocked = false
        p1.isOnlySpawningV1Villagers = false
        p1.vanillaVersion = "1.18.10"
        p1.limitedWorldHeight = 16
        p1.limitedWorldWidth = 16
        p1.isNetherType = false
        p1.eduSharedUriResource = EduSharedUriResource.EMPTY
        p1.isForceExperimentalGameplay = false
        p1.levelId = ""
        p1.levelName = "PowerNukkit Server"
        p1.premiumWorldTemplateId = ""
        p1.isTrial = false
        p1.authoritativeMovementMode = null
        p1.playerMovementSettings = SyncedPlayerMovementSettings()
        p1.playerMovementSettings.isServerAuthoritativeBlockBreaking = false
        p1.playerMovementSettings.movementMode = AuthoritativeMovementMode.CLIENT
        p1.playerMovementSettings.rewindHistorySize = 0
        p1.currentTick = currentTick
        p1.enchantmentSeed = 0
        p1.blockProperties.clear()
        p1.isInventoriesServerAuthoritative = false
        p1.multiplayerCorrelationId = ""
        p1.serverEngine = ""
        p1.blockRegistryChecksum = 0
        return p1
    }

    private fun computeAvailableEntityIdentifiersPacket(): AvailableEntityIdentifiersPacket {
        val p9 = AvailableEntityIdentifiersPacket()
        val bytes3 = this.javaClass.classLoader.getResourceAsStream("entity_identifiers.dat")!!
        val map2 = NbtUtils.createNetworkReader(bytes3).readTag() as NbtMap
        p9.identifiers = map2
        return p9
    }

    private fun computeBiomeDefinitionListPacket(): BiomeDefinitionListPacket {
        val p8 = BiomeDefinitionListPacket()
        val bytes = this.javaClass.classLoader.getResourceAsStream("biome_definitions.dat")!!
        val map = NbtUtils.createNetworkReader(bytes).readTag() as NbtMap
        p8.definitions = map
        return p8
    }
}
