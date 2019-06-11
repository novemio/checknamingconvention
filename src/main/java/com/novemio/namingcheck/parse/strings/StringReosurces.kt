package com.novemio.namingcheck.parse.strings

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml
class StringReosurces {

    @Element
    var list = mutableListOf<StringName>()
}