package nitrosrus.colorsquareapp.mvp.presenter.contract

import nitrosrus.colorsquareapp.baseMvp.BasePresenter
import nitrosrus.colorsquareapp.baseMvp.BaseView
import nitrosrus.colorsquareapp.mvp.presenter.list.IUserListPresenter

object UsersContract {

    interface View : BaseView {
        fun nextScreen(index: Int)
    }

    interface Presenter : BasePresenter<View> {
        fun getListPresenter(): IUserListPresenter
    }
}