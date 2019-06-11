package com.novemio.namingcheck.convention

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class ConventionConfig {

    var severity: Severity = Severity.ERROR

    @Json(name = "LayoutNaming")
     var layoutConvention: PrefixConvention? = null


    @Json(name = "DrawableNaming")
     var drawableConvention: PrefixConvention? = null

    @Json(name = "StringNaming")
    var stringConvention: StringConvention? = null


    fun getPrefixConvention(convention: ConventionName): PrefixConvention? {
        return when (convention) {
            ConventionName.LAYOUTS -> layoutConvention
            ConventionName.DRAWABLES -> drawableConvention


        }
    }

}


enum class ConventionName(var path:String) {
    LAYOUTS("layout"), DRAWABLES("drawable")

}



