package com.github.bedrockecs.storegen.zimpl

import com.github.bedrockecs.game.db.entity.EntityDBStorage
import com.github.bedrockecs.game.db.invitem.InvItemDBStorage
import com.github.bedrockecs.game.db.world.WorldDBSerial
import com.github.bedrockecs.game.db.world.WorldDBStorage
import com.github.bedrockecs.game.ext.DimensionConfiguration
import org.springframework.context.annotation.Bean

@DimensionConfiguration
class MockStoreGenDimensionConfiguration {
    @Bean
    fun worldDBStorage(testSerial: WorldDBSerial): WorldDBStorage {
        val heightRange = -64 to 320
        val generator = DirtPlatformChunkGenerator(heightRange)
        return WorldDBStorage.inMemory(heightRange, generator::generateChunk)
    }

    @Bean
    fun entityDBStorage(): EntityDBStorage {
        return EntityDBStorage.inMemory()
    }

    @Bean
    fun invitemDBStorage(): InvItemDBStorage {
        return InvItemDBStorage.inMemory()
    }
}
