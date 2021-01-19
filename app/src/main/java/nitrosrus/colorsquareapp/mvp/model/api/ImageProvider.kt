package nitrosrus.colorsquareapp.mvp.model.api

import android.widget.ImageView

interface ImageProvider {
    fun loadInto(urlString: String, view: ImageView)
}