package uz.larslion.navoiyqorakol.presentation.utils

import android.util.Log
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import kotlin.coroutines.Continuation
import kotlin.math.roundToInt

fun getMillisecondsFromTimestamp(time: String?): Long {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    try {
        val date = inputFormat.parse(time)
        return date.time
    } catch (e: Throwable) {
        Log.d("ErrorDate", e.message.toString())
    }
    return 0
}

fun format2DecimalPlaces(data: Double?): String {
    val bd = BigDecimal(data ?: 0.0)
    return try {
        bd.setScale(2, RoundingMode.CEILING).toString()
    } catch (e: Throwable) {
        Log.d("ErrorDate", e.message.toString())
        "0.0"
    }
}

fun formatPrintedDate(time: String?): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    val outputFormat = SimpleDateFormat("MMMM dd hh:mm a")
    try {
        val date = inputFormat.parse(time)
        return outputFormat.format(date)
    } catch (e: Throwable) {
        Log.d("ErrorDate", e.message.toString())
    }
    return "--"
}

fun formatOrderDate(time: String?, tZ: TimeZone, outFormat: String? = null): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    val outputFormat = SimpleDateFormat(outFormat ?: "MM-dd-yyyy hh:mm a z", Locale.ENGLISH).apply {
        timeZone = tZ
    }
    try {
        val date = inputFormat.parse(time)
        return outputFormat.format(date)
    } catch (e: Throwable) {
        Log.d("ErrorDate", e.message.toString())
    }
    return "--"
}

fun Int.toDoublePrice() = this / 100.0
fun Double.toIntPrice() = (this * 100).roundToInt()

suspend inline fun <T> suspendCoroutineWithTimeout(timeout: Long, crossinline block: (Continuation<T>) -> Unit ) : T? {
    var finalValue : T? = null
    withTimeoutOrNull(timeout) {
        finalValue = suspendCancellableCoroutine(block = block)
    }
    return finalValue
}