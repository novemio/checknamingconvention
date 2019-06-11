package com.novemio.namingcheck.html

import com.novemio.namingcheck.convention.NamingCheckResult


object HtmlStringsReportTab:BaseHtmlPage() {


    fun createPage(errorMap: HashMap<String, List<NamingCheckResult>>): String {
        val page = """
<div id="STRINGS" class="tabcontent">

<a name="top"></a>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td class="bannercell" rowspan="2"></td><td class="text-align:right">
</td>
</tr>
<tr>
</tr>
</table>
<hr size="1">
<h3>Summary</h3>
<table class="log" border="0" cellpadding="5" cellspacing="2" width="100%">
<tr>
<th>Files</th><th>Errors</th>
</tr>
<tr class="a">
<td>${errorMap.size}</td><td>${errorMap.values.count { it.isNotEmpty() }}</td>
</tr>
</table>
<hr size="1" width="100%" align="left">
<h3>Files</h3>
<table class="log" border="0" cellpadding="5" cellspacing="2" width="100%">
${createErrorTable(errorMap)}
</table>
<a href="#top">Back to top</a>
<hr size="1" width="100%" align="left">
</div>
""".trimIndent()

        return page
    }



}


