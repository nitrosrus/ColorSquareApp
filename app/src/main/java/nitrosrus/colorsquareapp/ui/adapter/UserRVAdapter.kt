package nitrosrus.colorsquareapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nitrosrus.colorsquareapp.R
import nitrosrus.colorsquareapp.mvp.presenter.list.IUserListPresenter
import nitrosrus.colorsquareapp.mvp.view.list.UserItemView

class UserRVAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    )

    override fun onBindViewHolder(holder: UserRVAdapter.ViewHolder, position: Int) {
        holder.pos = position
        presenter.bind(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), UserItemView {

        private val tvName: TextView = view.findViewById(R.id.tv_users)

        override var pos = -1

        override fun setName(name: String) {
            tvName.text = name
        }

        override fun setClickListener() {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(pos) }
        }
    }
}