package com.novemio.namingcheck

import com.novemio.namingcheck.convention.ConventionConfig
import com.novemio.namingcheck.convention.NamingCheckResult
import com.squareup.moshi.Moshi
import org.gradle.api.GradleException
import java.io.File


fun Any.getConventionConfig(configFile: String): ConventionConfig? {

    try {
        val json = File(configFile).bufferedReader().use { it.readText() }.toString()
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(ConventionConfig::class.java)
        return adapter.fromJson(json)
    } catch (e: Exception) {
        throw  GradleException("Error parsing config file: \n ${e.localizedMessage}")
    }
}


fun String.checkFileNamePrefix(prefix: Array<out String>): NamingCheckResult {

    prefix.forEach {
        if (this.startsWith(it)) {
            println("$this -------> OK")
            return     NamingCheckResult.OK
        }
    }
    println("$this -------> ERROR")
    return NamingCheckResult.DOESNT_CONTAIN_PREFIX("ERROR:  '$this'  doesn't start with  any prefix defined in config file")
}


fun String.checkNotStartWith(prefix: Array<out String>): NamingCheckResult {

    prefix.forEach {
        if (this.startsWith(it)) {
            return NamingCheckResult.START_WITH_PREFIX_NOT_ALLOWED("ERROR: '$it' is not allowed  as prefix")
        }
    }
    return NamingCheckResult.OK
}

fun String.checkNotContains(strings: Array<out String>): NamingCheckResult {

    strings.forEach {
        if (this.contains(it)) {
            return NamingCheckResult.CONTAINS_NOT_ALLOWED("ERROR: '$it' is not allowed  in string name")
        }
    }
    return NamingCheckResult.OK
}

fun String.checkUpperCase(enabled: Boolean): NamingCheckResult {

    if (this.contains(Regex(".*[A-Z].*"))) {
        return NamingCheckResult.UPPER_CASE_NOT_ALLOWED()
    }
    return NamingCheckResult.OK

}


fun String.checkFileNamePrefix(prefix: String) {


    if (this.startsWith(prefix)) {
        println("$this -------> OK")
        return
    }
    println("$this -------> ERROR")

}

fun File.listFilesForFolder(): ArrayList<String> {
    val list = arrayListOf<String>()
    this.listFiles()?.let {
        for (fileEntry in it) {
            if (fileEntry.isDirectory) {
                fileEntry.listFilesForFolder()
            } else {
                println(fileEntry.name)
                list.add(fileEntry.name)
            }
        }
    }
    return list
}

fun File.checkFileNamePrefixInFolder(vararg prefix: String): HashMap<String, List<NamingCheckResult>> {
    val map = hashMapOf<String, List<NamingCheckResult>>()
    this.listFiles()?.let {
        for (fileEntry in it) {
            if (fileEntry.isDirectory.not()) {
                val result = fileEntry.name.checkFileNamePrefix(prefix)
                val list= mutableListOf<NamingCheckResult>().apply {
                    if(result!=NamingCheckResult.OK) add(result)
                }
                map[fileEntry.absolutePath] = list
            }
        }
    }
    return map
}
