package com.test

import org.gradle.api.Plugin
import org.gradle.api.Project

class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        println("Hello from the GreetingPlugin")
    }
}
