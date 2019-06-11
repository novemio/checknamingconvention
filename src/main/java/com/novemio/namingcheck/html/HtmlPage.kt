package com.novemio.namingcheck.html

import com.novemio.namingcheck.convention.NamingCheckResult


object HtmlPage {


    fun createTabPage(tabs: List<HtmlTab>): String {
        val page = """
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
    .bannercell {
      border: 0px;
      padding: 0px;
    }
    body {
      margin-left: 10;
      margin-right: 10;
      font:normal 80% arial,helvetica,sanserif;
      background-color:#FFFFFF;
      color:#000000;
    }
    .a td {
      background: #efefef;
    }
    .b td {
      background: #fff;
    }
    th, td {
      text-align: left;
      vertical-align: top;
    }
    th {
      font-weight:bold;
      background: #ccc;
      color: black;
    }
    table, th, td {
      font-size:100%;
      border: none
    }
    table.log tr td, tr th {

    }
    h2 {
      font-weight:bold;
      font-size:140%;
      margin-bottom: 5;
    }
    h3 {
      font-size:100%;<td class="text-align:right">Designed for use with <a href="http://checkstyle.sourceforge.net/">CheckStyle</a> and <a href="http://jakarta.apache.org">Ant</a>.</td>
      font-weight:bold;
      background: #525D76;
      color: white;
      text-decoration: none;
      padding: 5px;
      margin-right: 2px;
      margin-left: 2px;
      margin-bottom: 0;
    }

/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
}


</style>
</head>
<body>
<br/br>
<h1>Naming Convention Report</h1>
<br/br>
${createTabs(tabs)}



<script>
function openReport(evt, reportName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(reportName).style.display = "block";
  evt.currentTarget.className += " active";
}
</script>









</body>
</html>
""".trimIndent()

        return page
    }



    fun createTabs(tabs: List<HtmlTab>): String {
        var tabsHtml = """<div class="tab">"""
        var tabPagesHtml="\n"

        tabs.forEach {
            tabsHtml=tabsHtml.plus(createTab(it)).plus("\n")
            tabPagesHtml=tabPagesHtml.plus(it.pageHtml).plus("\n")
        }
        tabsHtml=tabsHtml.plus("</div>")
            .plus("\n")
            .plus(tabPagesHtml)

return tabsHtml


    }

    fun createTab(tab:HtmlTab): String {

        return """<button class="tablinks" onclick="openReport(event, '${tab.name}')">${tab.name}</button>""".trimIndent()
    }


}


