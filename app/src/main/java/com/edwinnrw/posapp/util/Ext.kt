package com.edwinnrw.posapp.util

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.NumberFormat
import java.util.*

fun <T> singleIo(): SingleTransformer<T, T> {
    return SingleTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
fun numberFormat(value: String):String{
    val numberFormat = NumberFormat.getNumberInstance(Locale("in"))
    return numberFormat.format(value.replace(Regex("[^-?\\d+(.\\d+)?]"), "").toBigDecimal())
}
fun String.digitsOnly(): String{
    val regex = Regex("[^0-9]")
    return regex.replace(this, "")
}