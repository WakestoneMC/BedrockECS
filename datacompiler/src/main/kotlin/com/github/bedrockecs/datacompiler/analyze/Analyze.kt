package com.github.bedrockecs.datacompiler.analyze

import cn.nukkit.nbt.tag.ByteArrayTag
import cn.nukkit.nbt.tag.ByteTag
import cn.nukkit.nbt.tag.CompoundTag
import cn.nukkit.nbt.tag.DoubleTag
import cn.nukkit.nbt.tag.EndTag
import cn.nukkit.nbt.tag.FloatTag
import cn.nukkit.nbt.tag.IntArrayTag
import cn.nukkit.nbt.tag.IntTag
import cn.nukkit.nbt.tag.ListTag
import cn.nukkit.nbt.tag.LongTag
import cn.nukkit.nbt.tag.ShortTag
import cn.nukkit.nbt.tag.StringTag
import cn.nukkit.nbt.tag.Tag
import com.github.bedrockecs.datacompiler.parse.ParseResult
import com.github.bedrockecs.datacompiler.parse.ParsedBlockState
import com.github.bedrockecs.datacompiler.parse.persisentName
import com.github.bedrockecs.datacompiler.parse.runtimeId
import com.github.bedrockecs.datacompiler.parse.states
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.filter
import org.jetbrains.kotlinx.dataframe.api.forEach
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.map
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

val log = LoggerFactory.getLogger("analyze")

fun analyze(parsed: ParseResult): AnalysisResult {
    val itemDefs = analyzeItems(parsed)
    val blockItemIdMap = buildBlockItemIdMap(parsed)
    val (blockDefs, blockInstances) = analyzeBlocks(parsed, blockItemIdMap)

    blockDefs
        .filter { it.itemId == null }
        .forEach {
            log.warn("cannot find itemId for block [block={}]", it.persistentName)
        }

    return AnalysisResult(
        itemDefs,
        blockDefs,
        blockInstances
    )
}

private fun buildBlockItemIdMap(parsed: ParseResult): Map<String, Int> {
    // unobtainable blocks, so force them to be air anyway
    val forceToAirBlocks = setOf<String>(
        "minecraft:invisibleBedrock",
        "minecraft:movingBlock",
        "minecraft:pistonArmCollision",
        "minecraft:reserved6"
    )

    fun nameMapHeuristics(name: String): String {
        var ret = name.replace("minecraft:item.", "minecraft:")
        // fix spelling inconsistencies for mojang
        if (name == "minecraft:brewingstandblock") {
            ret = "minecraft:brewing_stand"
        }
        if (name == "minecraft:concrete_powder") {
            ret = "minecraft:concretePowder"
        }
        if (name == "minecraft:sealantern") {
            ret = "minecraft:seaLantern"
        }
        if (name == "minecraft:tripwire") {
            ret = "minecraft:tripWire"
        }
        return ret
    }

    var blockItemIds = parsed.requiredItems
        .filter { it.runtimeId < 255 }
        .map {
            val blockPersistentName = nameMapHeuristics(it.persisentName)
            blockPersistentName to it.runtimeId
        }
        .toMap()

    blockItemIds = blockItemIds + forceToAirBlocks.map { it to blockItemIds["minecraft:air"]!! }.toMap()

    return blockItemIds
}

fun analyzeItems(parsed: ParseResult): DataFrame<ItemTypeDefinition> {
    val itemDefs = parsed.requiredItems
        .filter { it.runtimeId > 255 }
        .map { ItemTypeDefinition(persistentName = it.persisentName, itemId = it.runtimeId) }
        .toDataFrame()
    return itemDefs
}

fun analyzeBlocks(
    parsed: ParseResult,
    blockPersistentNameToItemIdMap: Map<String, Int>
): Pair<DataFrame<BlockTypeDefinition>, DataFrame<BlockTypeInstance>> {
    val persistentNameGroupedBlockStates = parsed.blockStates
        .groupBy(ParsedBlockState::persisentName)
        .groups

    val defs = mutableListOf<BlockTypeDefinition>()
    val instances = mutableListOf<BlockTypeInstance>()
    persistentNameGroupedBlockStates
        .map { it[0].persisentName to it }
        .forEach { (persistentName, stateGroup) ->
            val candidatePropertyDefs = mutableListOf<Map<String, KClass<*>>>()
            val stateInstances = mutableListOf<BlockTypeInstance>()
            stateGroup.forEach { state ->
                val mappedStates = state.states.tags
                    .map { it.key to mapPrimitiveTagToClassValues(it.value) }
                    .toMap()

                val candidatePropertyDef = mappedStates.mapValues { it.value.first }
                val propertyValues = mappedStates.mapValues { it.value.second }

                candidatePropertyDefs.add(candidatePropertyDef)
                stateInstances.add(BlockTypeInstance(state.persisentName, state.runtimeId, propertyValues))
            }

            assert(candidatePropertyDefs.all { it == candidatePropertyDefs[0] })
            val candidatePropertyDef = candidatePropertyDefs[0]

            defs.add(
                BlockTypeDefinition(
                    persistentName = persistentName,
                    itemId = blockPersistentNameToItemIdMap[persistentName],
                    blockStateProps = candidatePropertyDef
                )
            )
            instances.addAll(stateInstances)
        }

    return defs.toDataFrame() to instances.toDataFrame()
}

/**
 * turns nbt primitives into language primitives, but on type level
 */
fun mapPrimitiveTagToClassValues(root: Tag): Pair<KClass<*>, Any> {
    return when (root) {
        // primitives
        is IntArrayTag -> IntArray::class to root.data
        is ByteArrayTag -> ByteArray::class to root.data
        is ByteTag -> Byte::class to (root.data.toByte())
        is DoubleTag -> Double::class to root.data
        is FloatTag -> Float::class to root.data
        is IntTag -> Int::class to root.data
        is LongTag -> Long::class to root.data
        is ShortTag -> Short::class to (root.data.toShort())
        is StringTag -> String::class to root.data
        // error case
        is EndTag, is CompoundTag, is ListTag<*> ->
            throw IllegalArgumentException("cannot unbox non-primitive tag $root!")
        else -> throw IllegalArgumentException("unexpected nbt tag type: $root")
    }
}
