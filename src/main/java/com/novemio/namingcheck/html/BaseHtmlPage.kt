package com.novemio.namingcheck.html

import com.novemio.namingcheck.convention.NamingCheckResult

open class BaseHtmlPage {
    fun createErrorTable(errorMap: HashMap<String, List<NamingCheckResult>>): String {

        var tabele = ""
        errorMap.toList().sortedBy { it.second.isEmpty() }.forEachIndexed { index, pair ->
            tabele = tabele.plus("\n").plus(
                """
<tr class="${if (index % 2 == 0) "a" else "b"} ">
<td><a href="${pair.first}">${pair.first}</a></td><td>${if (pair.second.isEmpty()) "OK" else createErrorMsg(pair.second)}</td>
</tr>
        """.trimIndent()
            )

        }

        return tabele
    }



    private fun createErrorMsg(list: List<NamingCheckResult>): String {

        var msg = "<font color=\"red\">"
        list.forEach {
            msg = msg.plus(it.msg).plus("<br><br>")
        }
        msg = msg.removeSuffix("<br><br>")
        msg = msg.plus("</font>")
        return msg
    }
}