package com.novemio.namingcheck.parse.strings

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "string")
class StringName{

    @Attribute

    var name:String = ""

}