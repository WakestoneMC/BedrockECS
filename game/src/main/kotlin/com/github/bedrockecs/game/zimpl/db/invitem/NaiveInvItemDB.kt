package com.github.bedrockecs.game.zimpl.db.invitem

import com.github.bedrockecs.game.db.common.ComponentMap
import com.github.bedrockecs.game.db.common.LifecycleType
import com.github.bedrockecs.game.db.common.MutableComponentMap
import com.github.bedrockecs.game.db.common.MutateType
import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.invitem.InvItemDBStorage
import com.github.bedrockecs.game.db.invitem.InvRef
import com.github.bedrockecs.game.db.invitem.InvSlotRef
import com.github.bedrockecs.game.db.invitem.data.InventoryMetadata
import com.github.bedrockecs.game.db.invitem.data.ItemComponent
import com.github.bedrockecs.game.db.invitem.data.ItemTypeComponent
import com.github.bedrockecs.game.db.invitem.event.InventoryLifecycleEvent
import com.github.bedrockecs.game.db.invitem.event.ItemMutationEvent
import com.github.bedrockecs.game.db.invitem.serial.SerialInventory
import com.github.bedrockecs.game.eventbus.EventBus
import com.github.bedrockecs.game.eventbus.publishFor
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class NaiveInvItemDB(evb: EventBus, private val storage: InvItemDBStorage) : InvItemDBInternal {

    private val invLifecycleEvent = evb.publishFor<InventoryLifecycleEvent>("invitemdb")

    private val itemMutationEvent = evb.publishFor<ItemMutationEvent>("invitemdb")

    private val lock = ReentrantLock()

    private data class Entry(
        val metadata: InventoryMetadata,
        val items: MutableList<MutableComponentMap<ItemComponent?>?>
    )

    private val entries = mutableMapOf<InvRef, Entry>()

    override fun create(metadata: InventoryMetadata): InvRef {
        val ref = InvRef(metadata.owner, metadata.name)
        lock.withLock {
            if (storage.listInventoriesFor(metadata.owner).contains(ref.name)) {
                throw IllegalArgumentException("inventory $ref already exists!")
            }

            val entry = Entry(metadata, (0 until metadata.size).map { null }.toMutableList())

            entries[ref] = entry
        }
        invLifecycleEvent.publish(null, InventoryLifecycleEvent(metadata, LifecycleType.CREATE))
        return ref
    }

    override fun destroy(ref: InvRef) {
        lock.withLock {
            val inv = entries[ref]
            if (inv != null) {
                inv.items.forEachIndexed { index, _ -> removeComponentsFromSlot(InvSlotRef(ref.owner, ref.name, index)) }
                invLifecycleEvent.publish(null, InventoryLifecycleEvent(inv.metadata, LifecycleType.DESTROY))
                entries.remove(ref)
            }
        }
        storage.removeInventory(ref)
    }

    override fun readMetadata(ref: InvRef): InventoryMetadata {
        lock.withLock {
            val inv = entries[ref]
            if (inv != null) {
                return inv.metadata
            } else {
                val serial = storage.readInventory(ref).join() ?: throw IllegalArgumentException("inventory $ref does not exists!")
                return serial.metadata
            }
        }
    }

    override fun place(slot: InvSlotRef, type: ItemTypeComponent, extras: ComponentMap<ItemComponent?>) {
        lock.withLock {
            val ref = InvRef(slot.owner, slot.name)
            val inv = entries[ref]

            val ex = extras.toMutableMap()
            ex[ItemTypeComponent::class.java] = type
            if (inv != null) {
                inv.items[slot.slot] = ex
            } else {
                var serial = storage.readInventory(ref).join() ?: throw IllegalArgumentException("inventory $ref does not exists!")
                serial = serial.copy(slots = serial.slots + mapOf(slot.slot to ex))
                storage.writeInventory(ref, serial).join()
            }

            ex.forEach { (type, c) ->
                if (c != null) {
                    itemMutationEvent.publish(c.type, ItemMutationEvent(slot, MutateType.ADD))
                }
            }
        }
    }

    override fun take(slot: InvSlotRef): Pair<ItemTypeComponent, ComponentMap<ItemComponent?>>? {
        val ret = removeComponentsFromSlot(slot)
        return if (ret == null) {
            null
        } else {
            val type = ret.remove(ItemTypeComponent::class.java)!! as ItemTypeComponent
            type to ret
        }
    }

    override fun clear(slot: InvSlotRef) {
        removeComponentsFromSlot(slot)
    }

    private fun removeComponentsFromSlot(slot: InvSlotRef): MutableComponentMap<ItemComponent?>? {
        val inv = InvRef(slot.owner, slot.name)
        lock.withLock {
            val entry = entries[inv]
            if (entry != null) {
                val components = entry.items[slot.slot]
                if (components != null) {
                    components.forEach { (ctype, c) ->
                        if (c != null) {
                            itemMutationEvent.publish(c.type, ItemMutationEvent(slot, MutateType.REMOVE))
                        }
                    }
                }
                entry.items[slot.slot] = mutableMapOf()
                return components
            } else {
                var serial = storage.readInventory(inv).join() ?: throw IllegalArgumentException("inventory $inv does not exists!")
                val components = serial.slots[slot.slot]
                if (components != null) {
                    components.forEach { (ctype, c) ->
                        if (c != null) {
                            itemMutationEvent.publish(c.type, ItemMutationEvent(slot, MutateType.REMOVE))
                        }
                    }
                    serial = serial.copy(slots = serial.slots - slot.slot)
                    storage.writeInventory(inv, serial).join()
                }
                return components?.toMutableMap()
            }
        }
    }

    // review line //

    override fun <T : ItemComponent> mutate(slot: InvSlotRef, clazz: Class<T>, func: (T?) -> T?) {
        val oldValue: T?
        val newValue: T?
        lock.withLock {
            val invRef = InvRef(slot.owner, slot.name)
            val inv = entries[invRef]
            if (inv != null) {
                val map = inv.items[slot.slot] ?: throw IllegalArgumentException("inventory slot $slot is empty!")
                oldValue = map[clazz] as T?
                newValue = func(oldValue)

                if (oldValue != null && newValue == null) {
                    itemMutationEvent.publish(oldValue.type, ItemMutationEvent(slot, MutateType.REMOVE))
                }

                if (newValue == null) {
                    map.remove(clazz)
                } else {
                    map[clazz] = newValue
                }

                if (oldValue != null && newValue == null) {
                    itemMutationEvent.publish(oldValue.type, ItemMutationEvent(slot, MutateType.REMOVE))
                }

                if (newValue == null) {
                    map.remove(clazz)
                } else {
                    map[clazz] = newValue
                }
            } else {
                var serial = storage.readInventory(invRef).join() ?: throw IllegalArgumentException("inventory $invRef not exists!")
                var map = serial.slots[slot.slot] ?: throw IllegalArgumentException("inventory slot $slot is empty!")
                oldValue = map[clazz] as T?
                newValue = func(oldValue)

                if (oldValue != null && newValue == null) {
                    itemMutationEvent.publish(oldValue.type, ItemMutationEvent(slot, MutateType.REMOVE))
                }

                if (newValue == null) {
                    map = map - clazz
                } else {
                    map = map + mapOf(clazz to newValue)
                }
                serial = serial.copy(slots = serial.slots + mapOf(slot.slot to map))
                storage.writeInventory(invRef, serial).join()
            }
        }
        if (oldValue == null && newValue != null) {
            itemMutationEvent.publish(newValue.type, ItemMutationEvent(slot, MutateType.ADD))
        } else if (oldValue != null && newValue != null) {
            itemMutationEvent.publish(newValue.type, ItemMutationEvent(slot, MutateType.UPDATE))
        }
    }

    override fun isEmpty(ref: InvSlotRef): Boolean {
        lock.withLock {
            val invRef = InvRef(ref.owner, ref.name)
            val inv = entries[invRef]
            if (inv != null) {
                return inv.items[ref.slot] == null
            } else {
                val serial = storage.readInventory(invRef).join() ?: throw IllegalArgumentException("inventory $ref not exists!")
                return serial.slots.containsKey(ref.slot)
            }
        }
    }

    override fun <T : ItemComponent> read(ref: InvSlotRef, clazz: Class<T>): T? {
        lock.withLock {
            val invRef = InvRef(ref.owner, ref.name)
            val inv = entries[invRef]
            if (inv != null) {
                return inv.items[ref.slot]?.get(clazz) as T?
            } else {
                val serial = storage.readInventory(invRef).join() ?: throw IllegalArgumentException("inventory $ref not exists!")
                return serial.slots[ref.slot]?.get(clazz) as T?
            }
        }
    }

    override fun list(ref: InvSlotRef): ComponentMap<ItemComponent> {
        lock.withLock {
            val invRef = InvRef(ref.owner, ref.name)
            val inv = entries[invRef]
            val components = if (inv != null) {
                inv.items[ref.slot]
            } else {
                val serial = storage.readInventory(invRef).join() ?: throw IllegalArgumentException("inventory $ref not exists!")
                serial.slots[ref.slot]
            }
            if (components == null) {
                return emptyMap()
            } else {
                val entries: MutableComponentMap<ItemComponent> = mutableMapOf()
                components.forEach {
                    if (it.value != null) {
                        entries.put(it.key, it.value!!)
                    }
                }
                return entries
            }
        }
    }

    override fun onEntityDestroying(eid: EntityID) {
        removeAllInventoryForEntity(eid)
    }

    private fun removeAllInventoryForEntity(eid: EntityID) {
        lock.withLock {
            entries
                .filterKeys { it.owner == eid }
                .forEach { destroy(it.key) }
            storage.listInventoriesFor(eid)
                .forEach { destroy(InvRef(eid, it)) }
        }
    }

    private fun load(serial: SerialInventory) {
        val ref = InvRef(serial.metadata.owner, serial.metadata.name)
        lock.withLock {
            if (entries.contains(ref)) {
                throw IllegalArgumentException("inventory $ref already exists!")
            }

            val list = (0 until serial.metadata.size)
                .map { null }
                .toMutableList<MutableComponentMap<ItemComponent?>?>()
            serial.slots.forEach { i, map -> list[i] = map.toMutableMap() }
            val entry = Entry(serial.metadata, list)

            entries[ref] = entry
        }
    }

    private fun unload(ref: InvRef): SerialInventory {
        lock.withLock {
            val inv = entries[ref] ?: throw IllegalArgumentException("inventory $ref does not exists!")
            val components = inv.items
                .mapIndexedNotNull { idx, comps ->
                    if (comps == null) {
                        null
                    } else {
                        idx to (comps as ComponentMap<ItemComponent?>)
                    }
                }
                .toMap()
            entries.remove(ref)
            return SerialInventory(inv.metadata, components)
        }
    }
}
