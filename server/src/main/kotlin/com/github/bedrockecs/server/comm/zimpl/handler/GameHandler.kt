package com.github.bedrockecs.server.comm.zimpl.handler

import com.github.bedrockecs.server.comm.server.NetworkConnection
import com.github.bedrockecs.server.common.zimpl.palette.PaletteStorage
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
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket
import com.nukkitx.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket
import com.nukkitx.protocol.bedrock.packet.NetworkSettingsPacket
import com.nukkitx.protocol.bedrock.packet.PlayStatusPacket
import com.nukkitx.protocol.bedrock.packet.SetCommandsEnabledPacket
import com.nukkitx.protocol.bedrock.packet.StartGamePacket
import io.netty.buffer.Unpooled
import org.springframework.stereotype.Component

/**
 * deals with everything about game protocol, spawning, interaction, world update......
 */
@Component
class GameHandler {
    suspend fun handle(conn: NetworkConnection) {
        serveInitializationPackets(conn)
        serveGame(conn)
    }

    private suspend fun serveInitializationPackets(connection: NetworkConnection) {
        val playerEntityID = 1L
        val playerPosition = Vector3f.from(100.0f, 64.0f, 100.0f)
        val playerPosition3i = Vector3i.from(100, 64, 100)
        val playerRotation = Vector2f.from(0.0, 0.0)
        // network setting & client cache
        connection.sendPacket(NetworkSettingsPacket().apply { compressionThreshold = 1 })
        // receive ClientCacheStatusPacket TODO: deals with ClientCacheStatusPacket

        // metadata initialization //
        connection.sendPacket(computeStartGamePacket(playerEntityID, playerPosition, playerRotation))
        // connection.sendPacket(computeItemComponentPacket()) TODO: send item component constants
        connection.sendPacket(SetCommandsEnabledPacket().apply { isCommandsEnabled = true })
        // AvailableCommandsPacket TODO: send available commands
        // AdventureSettingsPacket TODO: specify adventure settings
        connection.sendPacket(computeBiomeDefinitionListPacket())
        connection.sendPacket(computeAvailableEntityIdentifiersPacket())
        // CreativeContentPacket TODO: send creative content
        // CraftingDataPacket TODO: send crafting data content
    }

    private suspend fun serveGame(connection: NetworkConnection) {
        // game state //
        // PlayerListPacket TODO: send player list
        // SetTimePacket TODO: send time
        // PlayerFogPacket TODO: specify player fog

        // player state //
        // InventoryContentPacket
        // InventoryContentPacket
        // InventoryContentPacket
        // InventoryContentPacket
        // PlayerHotbarPacket

        // UpdateAttributesPacket
        // UpdateAttributesPacket
        // SetEntityDataPacket
        // SetEntityDataPacket
        // SetHealthPacket

        // execute respawn //
        // RespawnPacket
        // RespawnPacket
        // RespawnPacket

        // world state //

        // TODO: sends location of player & radius=64, range updated by chunk radius updated packet
        connection.sendPacket(
            NetworkChunkPublisherUpdatePacket().apply {
                position = playerPosition3i
                radius = 64
            }
        )
        // TODO: update sequence REQUEST_CHUNK_RADIUS -> CHUNK_RADIUS_UPDATED NETWORK_CHUNK_PUBLISHER_UPDATE are updated as follow-up

        // LEVEL_CHUNK for initial chunk content TODO: impl this out
        listChunksInRadius(playerPosition, 64)
            .forEach { (x, z) ->
                val packet = computeLevelChuckPacket(x, z)
                connection.sendPacket(packet, NetworkConnection.Latency.IMMEDIATELY)
            }

        // BLOCK_UPDATE for block updates, UPDATE_SUBCHUNK_BLOCKS for batched update? TODO: figure this out

        // TickSyncPacket

        // done //
        connection.sendPacket(PlayStatusPacket().apply { status = PlayStatusPacket.Status.PLAYER_SPAWN })
    }

    private fun listChunksInRadius(player: Vector3f, radius: Int): List<Pair<Int, Int>> {
        val x = player.x
        val z = player.z
        val minX = ((x - radius) / 16).toInt()
        val minZ = ((z - radius) / 16).toInt()
        val maxX = ((x + radius) / 16).toInt()
        val maxZ = ((z + radius) / 16).toInt()

        val ret = mutableListOf<Pair<Int, Int>>()
        for (x in minX..maxX) {
            for (z in minZ..maxZ) {
                ret.add(x to z)
            }
        }

        return ret
    }

    private fun computeLevelChuckPacket(chunkX: Int, chunkZ: Int): LevelChunkPacket {
        val dirtLayer = computeBlockPalette()
        val pureAir = computeAirBlockPalette()
        val biome = computeBiomePalette()

        val packet = LevelChunkPacket()
        packet.isCachingEnabled = false
        packet.blobIds.clear()

        packet.subChunkLimit = 0
        packet.isRequestSubChunks = false

        packet.subChunksLength = 12
        packet.chunkX = chunkX
        packet.chunkZ = chunkZ

        val buf = Unpooled.buffer()
        for (x in 0..6) {
            buf.writeByte(8)
            buf.writeByte(1)
            pureAir.writeTo(buf)
        }
        buf.writeByte(8)
        buf.writeByte(1)
        dirtLayer.writeTo(buf)
        for (x in 0..3) {
            buf.writeByte(8)
            buf.writeByte(1)
            pureAir.writeTo(buf)
        }

        for (x in 0..24) {
            biome.writeTo(buf)
        }

        buf.writeByte(0)

        // no-nbt

        packet.data = buf.array()

        return packet
    }

    private fun computeBiomePalette(): PaletteStorage {
        val airRID = 0
        val storage = PaletteStorage.createWithDefaultState(airRID)
        return storage
    }

    private fun computeBlockPalette(): PaletteStorage {
        val grassRID = 4484
        val airRID = 134
        val storage = PaletteStorage.createWithDefaultState(airRID)
        for (x in 0..15) {
            for (z in 0..15) {
                storage.setBlock(x, 0, z, grassRID)
            }
        }
        return storage
    }

    private fun computeAirBlockPalette(): PaletteStorage {
        val grassRID = 4484
        val airRID = 134
        val storage = PaletteStorage.createWithDefaultState(airRID)
        return storage
    }

    private suspend fun computeStartGamePacket(
        playerEntityID: Long,
        playerPosition: Vector3f,
        playerRotation: Vector2f
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
        p1.uniqueEntityId = playerEntityID
        p1.runtimeEntityId = playerEntityID
        p1.playerGameType = GameType.CREATIVE

        p1.playerPosition = playerPosition
        p1.rotation = playerRotation
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
        p1.currentTick = 0
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
