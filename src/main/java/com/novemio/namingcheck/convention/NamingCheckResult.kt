package com.novemio.namingcheck.convention
sealed class NamingCheckResult(val msg: String) {


    object OK : NamingCheckResult("OK")
    data class UPPER_CASE_NOT_ALLOWED(val error: String = "ERROR: Upper case is not allowed") : NamingCheckResult(error)
    data class START_WITH_PREFIX_NOT_ALLOWED(val error: String = "") : NamingCheckResult(error)
    data class CONTAINS_NOT_ALLOWED(val error: String = "") : NamingCheckResult(error)
    data class DOESNT_CONTAIN_PREFIX(val error: String = "") : NamingCheckResult(error)
}

fun NamingCheckResult.ifErrorAddToList(list: MutableList<NamingCheckResult>) {
    if (this != NamingCheckResult.OK) list.add(this)
}