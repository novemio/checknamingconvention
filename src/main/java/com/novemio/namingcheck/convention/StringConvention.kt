package com.novemio.namingcheck.convention

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class StringConvention {

    var camelCaseEnabled=false
    var notStartWith = arrayOf<String>()
    var doNotContain = arrayOf<String>()

}



