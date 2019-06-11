package com.novemio.namingcheck.convention

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PrefixConvention {

    var prefixes = arrayOf<String>()
}



