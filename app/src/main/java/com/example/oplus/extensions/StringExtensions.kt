package com.example.oplus.extensions

import com.google.gson.Gson
import com.khieu.nguyen.expandablecalendar.data.Day
import retrofit2.Call
import retrofit2.Callback
import java.text.SimpleDateFormat
import java.util.*

inline fun <reified T> String?.toObject(): T? {
    if(this==null || this=="null"){
        return null
    }
    var gson = Gson()
    var model = gson.fromJson(this, T::class.java)
    return model
}

inline fun <reified T> T.toJson(): String {
    return Gson().toJson(this)
}

fun Date.toSimpleString() : String {
    val format = SimpleDateFormat("dd-MM-yyy")
    return format.format(this)
}
fun String.toDate(): String? {
    val parser =  SimpleDateFormat("dd/MM/yyy")
    val format = SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH)
    return format.format(parser.parse(this))
}
