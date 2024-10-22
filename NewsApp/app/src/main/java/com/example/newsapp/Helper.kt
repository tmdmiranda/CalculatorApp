package com.example.newsapp

import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.parseDate() : Date?
{
    val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    return SimpleDateFormat(pattern).parse(this)
}

fun Date.toYYYMMDD() : String{
    val pattern = "yyyy-MM-dd"
    return SimpleDateFormat(pattern, Locale.getDefault())
        .format(this)
}

fun String.encodeURL() : String
{
    return URLEncoder.encode(this, "utf-8")
}