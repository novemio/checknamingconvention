package com.novemio.namingcheck.html

import com.novemio.namingcheck.convention.NamingCheckResult
import java.io.File

class HtmlBuilder {


    private var destinationPath: String? = null
    private var tabs = listOf<HtmlTab>()


    fun setPath(path: String): HtmlBuilder {
        destinationPath = path
        return this
    }


    fun setTabs(tabs: List<HtmlTab>): HtmlBuilder {
        this.tabs = tabs
        return this
    }


    fun build() {

        val file = File(destinationPath)

        if (file.exists().not()) {
            file.parentFile.mkdirs()
        }

        if (file.createNewFile()) {

            val page = HtmlPage.createTabPage(tabs).toByteArray()
            file.writeBytes(page)
        }
    }
}

