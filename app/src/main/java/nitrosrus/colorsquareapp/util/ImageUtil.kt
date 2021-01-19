package nitrosrus.colorsquareapp.util

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import nitrosrus.colorsquareapp.mvp.model.api.ImageProvider
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest

class ImageUtil(private val context: Context) : ImageProvider {

    override fun loadInto(urlString: String, view: ImageView) {
        Thread {
            checkImageBase(urlString, view)
        }.start()
    }

    private fun checkImageBase(urlString: String, view: ImageView) {
        val wrapper = ContextWrapper(context.applicationContext)
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${urlString.md5()}.png")

        if (file.exists()) {
            loadFromStorage(file.absolutePath, view)
        } else {
            loadFromInternet(urlString, view)
        }
    }

    private fun loadFromInternet(urlString: String, view: ImageView) {
        val drawable: Drawable
        val url = URL(urlString)
        val urlConnection = url.openConnection().apply {
            setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Linux; Android 11; sdk_gphone_x86_arm) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Mobile Safari/537.36"
            )
            doInput = true
        } as HttpURLConnection

        try {
            val inputStream = BufferedInputStream(urlConnection.inputStream)
            drawable = Drawable.createFromStream(inputStream, url.toString())
            setImageToView(drawable, view)
            saveInStorage(urlString, drawable)

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            urlConnection.disconnect()
        }
    }

    private fun loadFromStorage(path: String, view: ImageView) {
        val drawable = Drawable.createFromPath(path)!!
        setImageToView(drawable, view)
    }

    private fun saveInStorage(urlString: String, drawable: Drawable) {
        Thread {
            try {
                val bitmab = (drawable as BitmapDrawable).bitmap
                val wrapper = ContextWrapper(context.applicationContext)
                var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
                file = File(file, "${urlString.md5()}.png")
                val out = FileOutputStream(file)
                bitmab.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun setImageToView(drawable: Drawable, view: ImageView) {
        Handler(Looper.getMainLooper()).post {
            view.setImageDrawable(drawable)
        }
    }
}

private fun String.md5() = hash("MD5")

private fun String.hash(algoritm: String) =
    MessageDigest.getInstance(algoritm).digest(toByteArray())
        .fold("", { str, it -> "%02x".format(it) })