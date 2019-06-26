package com.novemio.namingcheck

import com.novemio.namingcheck.task.CheckNamingConvention
import org.gradle.api.Plugin
import org.gradle.api.Project


open class NamingConventionPlugin : Plugin<Project> {


    override fun apply(project: Project) {
        val task = project.tasks.create<CheckNamingConvention>(
            "checkNamingConvention",
            CheckNamingConvention::class.java
        ).apply {
            resourcePath = "${project.projectDir.absolutePath}/src/main/res/"

        }


    }
}