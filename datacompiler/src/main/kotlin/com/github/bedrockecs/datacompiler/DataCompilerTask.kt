package com.github.bedrockecs.datacompiler

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

abstract class DataCompilerTask : DefaultTask() {

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun execute() {
        // TODO: actually implement everything instead of commented out old code
//        val stream = CanonicalBlockStatesNBT.binaryStream()
//        val parsed = CanonicalBlockStatesNBT.parse(stream)
//        val analyzed = analyze(parsed)
//        val generated = codegen(analyzed)
//
//        generated.forEach {
//            it.writeTo(outputDir.get().asFile.toPath())
//        }
    }
}
