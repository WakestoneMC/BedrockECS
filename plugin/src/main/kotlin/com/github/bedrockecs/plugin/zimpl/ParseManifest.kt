package com.github.bedrockecs.plugin.zimpl

import com.github.bedrockecs.plugin.BedrockECSManifest
import org.yaml.snakeyaml.Yaml
import java.io.Reader

data class SerialManifest(
    var name: String? = null,
    var description: String = "",
    var version: String? = null,
    var classloader: String? = null,
    var configurations: List<String> = mutableListOf()
)

fun parseManifest(r: Reader): BedrockECSManifest {
    val serial = Yaml().loadAs(r, SerialManifest::class.java) ?: throw IllegalArgumentException("cannot parse manifest!")

    val name: String = serial.name ?: throw IllegalArgumentException("name must be provided!")

    val description = serial.description

    val version = serial.version ?: throw IllegalArgumentException("version must be provided!")
    val versionTuple = with(version) {
        val versionStrings = version.split(".")
        if (versionStrings.size != 3) {
            throw IllegalArgumentException("version string must consist of 3 entries separated by dot!")
        }
        val versions = versionStrings.mapIndexed { idx, e ->
            try {
                e.toUInt()
            } catch (ex: NumberFormatException) {
                throw IllegalArgumentException("version entry must be positive integer!")
            }
        }
        BedrockECSManifest.Version(versions[0], versions[1], versions[2])
    }

    val classLoader = when (serial.classloader) {
        null -> BedrockECSManifest.ClassLoader.ISOLATE
        "isolate" -> BedrockECSManifest.ClassLoader.ISOLATE
        "shared" -> BedrockECSManifest.ClassLoader.SHARED
        else -> throw IllegalArgumentException("unrecognized class loader spec ${serial.classloader}")
    }

    val configurations = serial.configurations

    return BedrockECSManifest(
        name,
        description,
        versionTuple,
        classLoader,
        configurations
    )
}
