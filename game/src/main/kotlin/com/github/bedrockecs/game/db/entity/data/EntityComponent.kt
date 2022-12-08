package com.github.bedrockecs.game.db.entity.data

import com.github.bedrockecs.game.db.common.Component
import com.badlogic.ashley.core.Component as AshleyComponent

/**
 * represents a component attached to an entity,
 * should be a dumb data container
 */
interface EntityComponent : Component, AshleyComponent
