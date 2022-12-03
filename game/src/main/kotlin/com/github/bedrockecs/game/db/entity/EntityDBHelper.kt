// ktlint-disable filename
/**
 * simplified shorthands for [EntityDB]
 */
package com.github.bedrockecs.game.db.entity

import com.github.bedrockecs.game.db.entity.data.EntityComponent

inline fun <reified T0 : EntityComponent> EntityDB.scan(
    config: EntityScanConfig = EntityScanConfig(),
    crossinline func: (EntityID, T0) -> Unit
) {
    scan(
        config,
        arrayOf(T0::class.java)
    ) { eid, arr ->
        val t0 = arr[0] as T0
        func(eid, t0)
    }
}

inline fun <reified T0 : EntityComponent, reified T1 : EntityComponent> EntityDB.scan(
    config: EntityScanConfig = EntityScanConfig(),
    crossinline func: (EntityID, T0, T1) -> Unit
) {
    scan(
        config,
        arrayOf(T0::class.java, T1::class.java)
    ) { eid, arr ->
        val t0 = arr[0] as T0
        val t1 = arr[1] as T1
        func(eid, t0, t1)
    }
}

inline fun <reified T0 : EntityComponent, reified T1 : EntityComponent, reified T2 : EntityComponent> EntityDB.scan(
    config: EntityScanConfig = EntityScanConfig(),
    crossinline func: (EntityID, T0, T1, T2) -> Unit
) {
    scan(
        config,
        arrayOf(T0::class.java, T1::class.java, T2::class.java)
    ) { eid, arr ->
        val t0 = arr[0] as T0
        val t1 = arr[1] as T1
        val t2 = arr[2] as T2
        func(eid, t0, t1, t2)
    }
}
