package nitrosrus.colorsquareapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nitrosrus.colorsquareapp.util.ImageUtil
import nitrosrus.colorsquareapp.R
import nitrosrus.colorsquareapp.mvp.model.api.ImageProvider
import nitrosrus.colorsquareapp.mvp.presenter.list.IPhotosListPresenter
import nitrosrus.colorsquareapp.mvp.view.list.PhotoItemView


class PhotoRVAdapter(val presenter: IPhotosListPresenter, context: Context) :
    RecyclerView.Adapter<PhotoRVAdapter.ViewHolder>() {

    private val loader: ImageProvider = ImageUtil(context = context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
    )

    override fun onBindViewHolder(holder: PhotoRVAdapter.ViewHolder, position: Int) {
        holder.pos = position
        presenter.bind(holder)
    }

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), PhotoItemView {

        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)

        override var pos = -1

        override fun setTitle(title: String) {
            tvTitle.text = title
        }

        override fun loadImage(url: String) {
            loader.loadInto(url, ivPhoto)
        }
    }
}