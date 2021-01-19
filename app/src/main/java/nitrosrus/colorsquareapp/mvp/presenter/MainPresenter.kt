package nitrosrus.colorsquareapp.mvp.presenter

import nitrosrus.colorsquareapp.mvp.view.MainView

interface MainPresenter<in V : MainView> {

    fun attachView(view: V)

}