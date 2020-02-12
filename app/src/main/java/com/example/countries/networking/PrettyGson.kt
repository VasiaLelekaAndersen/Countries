package com.example.countries.networking

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor

class PrettyJsonResponseLogger(
    private val gson: Gson
) : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        val logName = "okhttp"
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val prettyPrintJson = gson.toJson(JsonParser().parse(message))
                Log.d(logName, prettyPrintJson)
            } catch (m: JsonSyntaxException) {
                Log.d(logName, message)
            }
        } else {
            Log.d(logName, message)
            return
        }
    }
}