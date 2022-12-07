package com.github.bedrockecs.game.zimpl.db.entity

import com.github.bedrockecs.game.db.entity.EntityID
import com.github.bedrockecs.game.db.entity.EntityScanConfig
import com.github.bedrockecs.game.db.entity.data.EntityComponent
import com.github.bedrockecs.game.db.entity.data.EntityTypeComponent
import com.github.bedrockecs.game.zimpl.db.entity.testdata.EntityDBUtil.withTestDB
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestComponentA
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestComponentB
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestTypeComponents.TYPE_A
import com.github.bedrockecs.game.zimpl.db.entity.testdata.TestTypeComponents.TYPE_B
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EntityDBImplScanTest {
    @Test
    fun scanWorks() {
        withTestDB { evb, db ->
            val eid1 = db.create(TYPE_A, setOf(TestComponentA(test = "test"), TestComponentB()))
            val eid2 = db.create(TYPE_B, setOf(TestComponentA(test = "test2")))
            val eid3 = db.create(TYPE_A, setOf(TestComponentB()))

            val visitedEntities = mutableSetOf<EntityID>()
            val visitedComponents = mutableSetOf<Array<EntityComponent>>()
            db.scan(
                EntityScanConfig(),
                arrayOf(EntityTypeComponent::class.java, TestComponentA::class.java)
            ) { eid, arr ->
                visitedEntities.add(eid)
                visitedComponents.add(arr)
            }

            assertThat(visitedEntities).containsExactly(eid1, eid2)
            assertThat(visitedComponents).containsExactly(
                arrayOf(TYPE_A, TestComponentA(test = "test")),
                arrayOf(TYPE_B, TestComponentA(test = "test2"))
            )
        }
    }

    // mutable scan works //

    @Test
    fun mutatingScanWorks() {
        withTestDB { evb, db ->
            val eid1 = db.create(TYPE_A, setOf(TestComponentA(test = "test"), TestComponentB()))
            val eid2 = db.create(TYPE_B, setOf(TestComponentA(test = "test2")))
            val eid3 = db.create(TYPE_A, setOf(TestComponentB()))

            val visitedEntities = mutableSetOf<EntityID>()
            val visitedComponents = mutableSetOf<Array<EntityComponent>>()
            db.mutatingScan(
                EntityScanConfig(),
                arrayOf(EntityTypeComponent::class.java, TestComponentA::class.java)
            ) { eid, arr ->
                visitedEntities.add(eid)
                visitedComponents.add(arr)
                when (eid) {
                    eid1 -> arrayOf(arr[0], null)
                    eid2 -> arrayOf(arr[0], TestComponentA(test = "updated"))
                    else -> throw AssertionError("impossible branch")
                }
            }

            assertThat(visitedEntities).containsExactly(eid1, eid2)
            assertThat(visitedComponents).containsExactly(
                arrayOf(TYPE_A, TestComponentA(test = "test")),
                arrayOf(TYPE_B, TestComponentA(test = "test2"))
            )

            assertThat(db.list(eid1)).containsExactly(TYPE_A, TestComponentB())
            assertThat(db.list(eid2)).containsExactly(TYPE_B, TestComponentA(test = "updated"))
        }
    }
}
