package com.novemio.namingcheck.task.checker

import com.novemio.namingcheck.convention.ConventionConfig





open class BaseChecker(    protected val conventionConfig: ConventionConfig) {

    val violationMsg= "Naming rule violations were found"
    val everythingOk= "Everything is OK"



}
