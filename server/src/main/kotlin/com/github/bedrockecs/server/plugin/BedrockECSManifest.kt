package com.github.bedrockecs.server.plugin

import kotlinx.serialization.Serializable

/**
 * schema for the bedrockecs.yaml inside jar files
 */
@Serializable
data class BedrockECSManifest(
    /**
     * name of the plugin, preferably snake-cased
     */
    val name: String,
    /**
     * description of the plugin
     */
    val description: String = "",
    /**
     * semver format version
     */
    val version: Version = Version(0U, 1U, 0U),
    /**
     * the classloader to put this plugin jar into
     */
    val classLoader: ClassLoader = ClassLoader.ISOLATE,
    /**
     * list of fully-qualified spring configuration classes names
     */
    val configurations: List<String>
) {
    enum class ClassLoader {
        SHARED,
        ISOLATE
    }

    @Serializable
    data class Version(
        val major: UInt,
        val minor: UInt,
        val patch: UInt
    )
}
