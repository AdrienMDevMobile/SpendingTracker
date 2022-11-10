package com.micheldr.spendingtracker.data

fun String?.toAutoDeleteChoice() = when (this) {
    "ONE_WEEK" -> AutoDeleteChoice.ONE_WEEK
    "ONE_MONTH" -> AutoDeleteChoice.ONE_MONTH
    "THREE_MONTH" -> AutoDeleteChoice.THREE_MONTH
    "ONE_YEAR" -> AutoDeleteChoice.ONE_YEAR
    else -> AutoDeleteChoice.ONE_YEAR
}