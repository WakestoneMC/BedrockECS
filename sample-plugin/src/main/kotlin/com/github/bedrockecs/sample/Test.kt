package com.github.bedrockecs.sample // ktlint-disable filename

fun main() {
    SampleCoreConfiguration::class.java.annotations.forEach { println(it) }
}
