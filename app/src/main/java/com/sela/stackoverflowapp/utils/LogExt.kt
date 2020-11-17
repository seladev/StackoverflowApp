package com.sela.superdomarket.utils

import android.util.Log

/**
 * Log Extension
 */

fun Any.logDebug(message:String){
    Log.d(this::class.simpleName, message)
}

fun Any.logError(message:String){
    Log.e(this::class.simpleName, message)
}

fun Any.logInfo(message:String){
    Log.i(this::class.simpleName, message)
}

fun Any.logView(message:String){
    Log.d(this::class.simpleName, " ~~~~~~~~~~~~~~~~ $message  ~~~~~~~~~~~~~~~~")
}