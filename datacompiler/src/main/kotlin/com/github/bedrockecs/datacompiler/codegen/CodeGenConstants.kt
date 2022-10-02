package com.github.bedrockecs.datacompiler.codegen

import com.squareup.kotlinpoet.ClassName

const val BASE_PACKAGE = "com.github.bedrockecs.vanilla.data"
const val BLOCKS_BASE_PACKAGE = "$BASE_PACKAGE.blocks"
const val ITEMS_BASE_PACKAGE = "$BASE_PACKAGE.items"

val VANILLA_ITEM_TYPE = ClassName(ITEMS_BASE_PACKAGE, "VanillaItemType")
val VANILLA_ITEM_TYPE_COMPANION = ClassName(ITEMS_BASE_PACKAGE, "VanillaItemType", "Companion")
val ITEM_ID = ClassName(ITEMS_BASE_PACKAGE, "ItemID")
