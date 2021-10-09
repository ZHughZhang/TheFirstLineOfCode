package com.ebsig.httpsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    private val TAG: String = MainActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendRequestBtn.setOnClickListener {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.42/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService = retrofit.create(AppService::class.java)

            appService.getAppData().enqueue(object :Callback<List<App>>{
                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {

                val list = response.body()
                    if (list!=null) {
                        for (app in list) {
                            Log.e(TAG, "onResponse: id is ${app.id}" )
                            Log.e(TAG, "onResponse: name is ${app.name}" )
                            Log.e(TAG, "onResponse: version is ${app.version}" )
                        }
                    }
                }

                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    t.printStackTrace()
                }

            })




        }

    }
    suspend fun <T> Call<T>.await():T{
        return suspendCoroutine {
            continuation ->
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    response.body()?.let {
                        continuation.resume(it)
                    }
                    if (response.body() == null) {
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }

    private fun sendRequestWithOkhttp(){
        thread {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://192.168.0.42/get_data.json")
                .build()

            val response = client.newCall(request).execute()
            val responseData = response.body?.string()
            if (responseData != null) {
                parseJSONWidthJSONObject(responseData)
                parseJSONWidthGSON(responseData)
            }
        }
    }
    private fun sendRequestWithHttpURLConnection() {
        thread {
            var connection:HttpURLConnection? = null
            try {
                var response = StringBuilder()
                val url = URL("https://www.baidu.com/")
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
                Log.e(TAG, "sendRequestWithHttpURLConnection: "+response.toString() )
//                showResponse(response.toString())
                ParseXMLWidthPull(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, "sendRequestWithHttpURLConnection: "+e.message )
            }finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(string: String) {
        runOnUiThread {
            responseText.text = string
        }
    }
    private fun ParseXMLWidthPull(string: String) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(string))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when (eventType) {
                    XmlPullParser.START_TAG->{
                        when(nodeName){
                            "id"->id = xmlPullParser.nextText()
                            "name"->name = xmlPullParser.nextText()
                            "version"->version = xmlPullParser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG->{
                        if ("app" == nodeName) {
                            Log.e(TAG, "ParseXMLWidthPull: id $id" )
                            Log.e(TAG, "ParseXMLWidthPull: name $name" )
                            Log.e(TAG, "ParseXMLWidthPull: version $version" )
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun ParseXMLWidthSax(string: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()
            xmlReader.contentHandler = handler
            xmlReader.parse(InputSource(StringReader(string)))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun parseJSONWidthJSONObject(jsonData:String) {
        val jsonArray = JSONArray(jsonData)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val name = jsonObject.getString("name")
            val version = jsonObject.getString("version")
            Log.e(TAG, "parseJSONWidthJSONObject: id is $id")
            Log.e(TAG, "parseJSONWidthJSONObject: name is $name")
            Log.e(TAG, "parseJSONWidthJSONObject: version is $version")
        }
    }
    private fun parseJSONWidthGSON(json:String){
        val gson = Gson()
        val typeOf = object :TypeToken<List<App>>(){}.type
        val appList = gson.fromJson<List<App>>(json,typeOf)

        for (app in appList) {
            Log.e(TAG, "parseJSONWidthGSON:id is ${app.id} " )
            Log.e(TAG, "parseJSONWidthGSON:name is ${app.name} " )
            Log.e(TAG, "parseJSONWidthGSON:version is ${app.version} " )
        }
    }

}