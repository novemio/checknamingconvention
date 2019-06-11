package com.novemio.namingcheck.task.checker

import com.novemio.namingcheck.checkFileNamePrefixInFolder
import com.novemio.namingcheck.convention.ConventionConfig
import com.novemio.namingcheck.convention.ConventionName
import com.novemio.namingcheck.html.HtmlPrefixesTab
import com.novemio.namingcheck.html.HtmlTab
import org.gradle.api.DefaultTask
import java.io.File

class PrefixConventionChecker(
    private val task: DefaultTask,
    private val conventionName: ConventionName,
     conventionConfig: ConventionConfig,
    private val resourcePath: String? = null
) :BaseChecker(conventionConfig){


    private var message = ""

    fun checkConvention(): Result {




        val prefixConvention = conventionConfig.getPrefixConvention(conventionName)


        println()
        println("************ Check Naming ConventionConfig For $conventionName ********* \n")

        val hashMap =
            File("$resourcePath/${conventionName.path}").checkFileNamePrefixInFolder(*prefixConvention!!.prefixes)


        val tabPage = HtmlPrefixesTab.createPage(conventionName.name, hashMap)



        println("\n\n")

        message = if (hashMap.values.count { it.isNotEmpty() } > 0) {
            "$conventionName: $violationMsg"
        } else {
            "$conventionName: $everythingOk"
        }



        return Result(hashMap.values.count { it.isNotEmpty() }, message, HtmlTab(conventionName.name,tabPage))

    }
}





