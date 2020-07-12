package com.yoite.chibissapp.common

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
private val SERVER_DATE_FORMAT: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").apply {
    timeZone = TimeZone.getTimeZone("UTC")
}

/**
 * Преобразует дату в миллисекундах в дату формата "2020-11-06T00:00:00.0000000+03:00"
 *
 * @param timeInMilliseconds требуемая дата для преобразования
 */
fun convertTimeToServerStringFormat(timeInMilliseconds: Long): String {
    return SERVER_DATE_FORMAT.format(Date(timeInMilliseconds))
}

/**
 * Преобразует дату формате "2020-11-06T00:00:00.0000000+03:00" в миллисекунды
 *
 * @param date требуемая дата преобразования
 */
fun convertStringDateToLong(date: String): Long {
    return SERVER_DATE_FORMAT.parse(date).time
}

fun dateLongToString(date: Long): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
    val dateTime = Date(date)
    return formatter.format(dateTime)
}