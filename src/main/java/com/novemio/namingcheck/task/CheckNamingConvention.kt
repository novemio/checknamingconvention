package com.novemio.namingcheck.task

import com.novemio.namingcheck.convention.ConventionName
import com.novemio.namingcheck.convention.Severity
import com.novemio.namingcheck.getConventionConfig
import com.novemio.namingcheck.html.HtmlBuilder
import com.novemio.namingcheck.task.checker.PrefixConventionChecker
import com.novemio.namingcheck.task.checker.StringsConventionChecker
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

open class CheckNamingConvention : DefaultTask() {

    var configFile: String? = null
    var resourcePath: String? = null

    @TaskAction
    fun taskAction() {
        println(resourcePath)

        if (configFile == null) {
            throw GradleException("Config file not found")
        }

        val conventionConfig =
            getConventionConfig(configFile!!)
                ?: throw GradleException("Config file is invalid, please check properties ")


        val stringsResult = StringsConventionChecker(this, conventionConfig, resourcePath).checkConvention()


        val layoutResult =
            PrefixConventionChecker(
                this,
                ConventionName.LAYOUTS,
                conventionConfig,
                resourcePath
            ).checkConvention()
        val drawableResult =
            PrefixConventionChecker(
                this,
                ConventionName.DRAWABLES,
                conventionConfig,
                resourcePath
            ).checkConvention()


        println(layoutResult.message)
        println(drawableResult.message)
        println(stringsResult.message)

        val reportPath =
            "${project.projectDir}/build/reports/checknamingconvention/name-convention-check-result.html"

        HtmlBuilder()
            .setPath(reportPath)
            .setTabs(listOf(layoutResult.htmlTab,drawableResult.htmlTab,stringsResult.htmlTab))
            .build()


        if (layoutResult.errorCount + drawableResult.errorCount + stringsResult.errorCount > 0) {

            val message = "Check errors at: file://$reportPath"

            if (conventionConfig.severity == Severity.ERROR) {
                throw GradleException(message)
            } else {
                println(message)

            }


        }


    }


}



