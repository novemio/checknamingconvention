package com.novemio.namingcheck.task.checker

import com.novemio.namingcheck.*
import com.novemio.namingcheck.convention.NamingCheckResult
import com.novemio.namingcheck.convention.ConventionConfig
import com.novemio.namingcheck.convention.ifErrorAddToList
import com.novemio.namingcheck.html.HtmlStringsReportTab
import com.novemio.namingcheck.html.HtmlTab
import com.novemio.namingcheck.parse.strings.StringName
import com.novemio.namingcheck.parse.strings.StringReosurces
import com.tickaroo.tikxml.TikXml
import okio.buffer
import okio.source
import org.gradle.api.DefaultTask
import java.io.File

class StringsConventionChecker(
    val task: DefaultTask,
    conventionConfig: ConventionConfig,
    val resourcePath: String?
):BaseChecker(conventionConfig) {


    private val statusMap = hashMapOf<String, List<NamingCheckResult>>()


    private fun parseStringXMl(stringFile: File) {
        println("\n******** Parse ${stringFile.name} ***********\n")


        conventionConfig.stringConvention?.let {convention->


            val source = stringFile.source().buffer()
            val build = TikXml.Builder().build()
            val stringReosurces = build.read<StringReosurces>(source, StringReosurces::class.java)

            stringReosurces.list.forEach { it ->
                val checkPrefix = it.name.checkNotStartWith(convention.notStartWith)
                val checkContains = it.name.checkNotContains(convention.doNotContain)
                val upperCase = it.name.checkUpperCase(convention.camelCaseEnabled)

                parseCheckResults(stringFile, it, checkPrefix, checkContains, upperCase)

                statusMap[it.name].let { errorList ->
                    if (errorList?.isEmpty()!!) {
                        println("${it.name} -------------> OK")
                    } else {
                        val SUFFIX = "   &&   "
                        var errorMsg = ""
                        errorList.forEach {
                            errorMsg = errorMsg.plus("${it.msg}$SUFFIX")
                        }

                        errorMsg = errorMsg.removeSuffix(SUFFIX)
                        println("${it.name} -------------> $errorMsg")
                    }
                }
            }


        }

    }

    private fun parseCheckResults(
        stringFile: File,
        it: StringName,
        checkPrefix: NamingCheckResult,
        checkContains: NamingCheckResult,
        upperCase: NamingCheckResult
    ) {

        val list = mutableListOf<NamingCheckResult>()
        checkContains.ifErrorAddToList(list)
        checkPrefix.ifErrorAddToList(list)
        upperCase.ifErrorAddToList(list)
        statusMap[it.name] = list

    }


    fun checkConvention(): Result {


        val valueFile = File("$resourcePath/values")

        if (valueFile.exists() && valueFile.isDirectory) {

            valueFile.listFiles().forEach {

                if (it.isFile && it.name.startsWith("strings")) {
                    parseStringXMl(it)
                }
            }
        }

        val page = HtmlStringsReportTab.createPage(statusMap)


        val message = if (statusMap.values.count { it.isNotEmpty() } > 0) {

            "STRINGS :$violationMsg"
        } else {
            "STRINGS : $everythingOk"
        }

        return Result(statusMap.values.count { it.isNotEmpty() }, message, HtmlTab("STRINGS",page))

    }

}




