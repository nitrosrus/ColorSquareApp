package nitrosrus.colorsquareapp.mvp.presenter.list

import androidx.lifecycle.MutableLiveData
import nitrosrus.colorsquareapp.mvp.model.entity.Photo
import nitrosrus.colorsquareapp.mvp.view.list.PhotoItemView

interface IPhotosListPresenter {
    fun getLiveData(): MutableLiveData<MutableList<Photo>>
    var itemClickListener: ((Int) -> Unit)?
    fun getCount(): Int
    fun bind(view: PhotoItemView)

}