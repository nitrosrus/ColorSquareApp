package nitrosrus.colorsquareapp.util


import androidx.annotation.WorkerThread
import nitrosrus.colorsquareapp.mvp.model.api.ApiPathUrl
import nitrosrus.colorsquareapp.mvp.model.api.IJSONLoader
import java.net.HttpURLConnection
import java.net.URL

class JSONLoader() : IJSONLoader {

    override fun getUsers(): String {
        return loadData(ApiPathUrl.PATH_USERS)
    }

    override fun getPhotos(): String {
        return loadData(ApiPathUrl.PATH_PHOTOS)
    }

    @WorkerThread
    private fun loadData(uri: String): String {
        var data = ""
        val httpURLConnection = URL(uri).openConnection() as HttpURLConnection
        httpURLConnection.apply {
            connectTimeout = 10000
            requestMethod = "GET"
            doInput = true
        }
        try {
            data = httpURLConnection.inputStream.bufferedReader().readText()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            httpURLConnection.disconnect()
        }
        return data
    }
}