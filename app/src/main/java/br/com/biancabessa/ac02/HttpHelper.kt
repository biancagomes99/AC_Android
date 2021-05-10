package br.com.biancabessa.ac02

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

object HttpHelper {

    val JSON = MediaType.parse("application/json; charset=utf-8")

    var client = OkHttpClient()

    fun get(url: String): String{
        val request = Request.Builder().url(url).get().build()
        return getJson(request)
    }

    fun post(url: String, json: String) : String {
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    fun delete(url: String) : String{
        val request = Request.Builder().url(url).delete().build()
        return getJson(request)
    }

    private fun getJson(request: Request): String{
        val response = client.newCall(request).execute()
        val body = response.body()
        if (body != null) {
            val json = body?.string()
            return json
        }

        return ""
    }
}