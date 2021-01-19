package nitrosrus.colorsquareapp.mvp.presenter

import androidx.lifecycle.MutableLiveData
import nitrosrus.colorsquareapp.baseMvp.BasePresenterImpl
import nitrosrus.colorsquareapp.mvp.model.api.IJSONLoader
import nitrosrus.colorsquareapp.mvp.model.api.IJSONParser
import nitrosrus.colorsquareapp.util.JSONLoader
import nitrosrus.colorsquareapp.util.JSONParser
import nitrosrus.colorsquareapp.mvp.model.entity.Photo
import nitrosrus.colorsquareapp.mvp.presenter.contract.PhotosContract
import nitrosrus.colorsquareapp.mvp.presenter.list.IPhotosListPresenter
import nitrosrus.colorsquareapp.mvp.view.list.PhotoItemView


class PhotosPresenter(var index: Int) : BasePresenterImpl<PhotosContract.View>(),
    PhotosContract.Presenter {

    private val jsonLoader: IJSONLoader = JSONLoader()
    private val jsonParser: IJSONParser = JSONParser()
    private var listPresenter = PhotosListPresenter()
    private lateinit var view: PhotosContract.View

    class PhotosListPresenter : IPhotosListPresenter {

        val list = MutableLiveData<MutableList<Photo>>()

        override fun getLiveData() = list

        override var itemClickListener: ((Int) -> Unit)? = null

        override fun getCount() = list.value?.size ?: -1

        override fun bind(view: PhotoItemView) {
            view.setTitle(list.value!![view.pos].title)
            view.loadImage(list.value!![view.pos].url)
        }
    }

    override fun attachView(view: PhotosContract.View) {
        super.attachView(view)
        this.view = view
        Thread { loadData() }.start()
    }

    override fun loadData() {
        toItemSelect(jsonParser.parseToPhotosList(jsonLoader.getPhotos()))
    }

    private fun toItemSelect(allPhotos: MutableList<Photo>) {
        listPresenter.list.postValue(allPhotos.filter { it.albumId == index } as MutableList<Photo>)
    }

    override fun getListPresenter(): IPhotosListPresenter {
        return listPresenter
    }
}