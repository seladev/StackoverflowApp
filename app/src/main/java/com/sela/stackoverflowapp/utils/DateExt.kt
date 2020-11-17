package com.sela.stackoverflowapp.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by seladev
 */

///DATE AND TIME
private var FULL_MONTH_YEAR_FORMAT_24 = SimpleDateFormat("MMM dd yyyy HH:mm", Locale.getDefault())
fun Long.getFullMonthYearClockFormat(): String =  Date(TimeUnit.SECONDS.toMillis(this)).getFullMonthYearClockFormat()
fun Date.getFullMonthYearClockFormat(): String = FULL_MONTH_YEAR_FORMAT_24.format(this)


fun Long.getDate(): Date {
    return Date(this)
}