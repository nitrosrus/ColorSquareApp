package nitrosrus.colorsquareapp.mvp.presenter.contract

import nitrosrus.colorsquareapp.baseMvp.BasePresenter
import nitrosrus.colorsquareapp.baseMvp.BaseView
import nitrosrus.colorsquareapp.mvp.presenter.list.IPhotosListPresenter

object PhotosContract {

    interface View : BaseView {
    }

    interface Presenter : BasePresenter<View> {
        fun getListPresenter(): IPhotosListPresenter
    }
}