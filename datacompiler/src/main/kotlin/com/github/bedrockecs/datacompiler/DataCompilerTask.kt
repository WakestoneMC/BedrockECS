package com.github.bedrockecs.datacompiler

import com.github.bedrockecs.datacompiler.analyze.analyze
import com.github.bedrockecs.datacompiler.codegen.codeGen
import com.github.bedrockecs.datacompiler.parse.parse
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

abstract class DataCompilerTask : DefaultTask() {
    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun execute() {
        val parsed = parse()
        val analyzed = analyze(parsed)
        val generated = codeGen(analyzed)

        generated.forEach {
            it.writeTo(outputDir.get().asFile.toPath())
        }
    }
}
