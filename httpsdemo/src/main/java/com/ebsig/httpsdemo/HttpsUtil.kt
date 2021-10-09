package com.ebsig.httpsdemo

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread

object HttpsUtil {
    fun sendHttpRequest(address:String):String{
        var connection:HttpsURLConnection?=null

        try {
            val response = StringBuilder()
            val url = URL(address)
            connection = url.openConnection() as HttpsURLConnection
            connection.connectTimeout = 800
            connection.readTimeout = 800
            val input = connection.inputStream
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    response.append(it)
                }
            }
            return response.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return e.message.toString()
        }finally {
            connection?.disconnect()
        }

    }
    fun sendHttpRequest(address:String,listener: HttpCallbackListener){
        thread {
            var connection:HttpsURLConnection?=null

            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpsURLConnection
                connection.connectTimeout = 800
                connection.readTimeout = 800
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                listener.onFinish(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                listener.onError(e)
            }finally {
                connection?.disconnect()
            }
        }
    }

    fun sendOkhtttpRequset(address: String,callback:okhttp3.Callback){
        val client = OkHttpClient()

        val request = Request.Builder().url(address).build()

        client.newCall(request).enqueue(callback)
    }

}