package nitrosrus.colorsquareapp.mvp.presenter

import nitrosrus.colorsquareapp.mvp.view.MainView

class MainPresenterImpl : MainPresenter<MainView> {

    lateinit var view: MainView

    override fun attachView(view: MainView) {
        this.view = view
        view.init()
    }

}