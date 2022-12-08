package com.github.bedrockecs.game.io.action

enum class BlockFace {
    BOTTOM, // 0
    TOP, // 1
    NORTH, // 2
    SOUTH, // 3
    EAST, // 4
    WEST; // 5

    companion object {
        fun parseInt(v: Int): BlockFace {
            return when (v) {
                0 -> BOTTOM
                1 -> TOP
                2 -> NORTH
                3 -> SOUTH
                4 -> EAST
                5 -> WEST
                else -> throw IllegalArgumentException("$v does not have corresponding block face!")
            }
        }
    }

    fun toInt(): Int {
        return ordinal
    }
}
