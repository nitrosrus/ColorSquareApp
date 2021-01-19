package nitrosrus.colorsquareapp.mvp.presenter.list

import androidx.lifecycle.MutableLiveData
import nitrosrus.colorsquareapp.mvp.model.entity.User
import nitrosrus.colorsquareapp.mvp.view.list.UserItemView

interface IUserListPresenter {
    fun getLiveData(): MutableLiveData<MutableList<User>>
    var itemClickListener: ((Int) -> Unit)?
    fun getCount(): Int
    fun bind(view: UserItemView)

}