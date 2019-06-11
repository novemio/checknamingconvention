package com.novemio.namingcheck

import com.novemio.namingcheck.convention.ConventionName
import com.novemio.namingcheck.task.CheckConventionTask
import org.gradle.api.Plugin
import org.gradle.api.Project


open class NamingConventionPlugin : Plugin<Project> {


    override fun apply(project: Project) {
        val task = project.tasks.create<CheckConventionTask>(
            "checkNamingConvention",
            CheckConventionTask::class.java
        ) { task ->
            task.resourcePath = "${project.projectDir.absolutePath}/src/main/res/"
        }


    }
}