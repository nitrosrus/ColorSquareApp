package nitrosrus.colorsquareapp.mvp.presenter

import androidx.lifecycle.MutableLiveData
import nitrosrus.colorsquareapp.baseMvp.BasePresenterImpl
import nitrosrus.colorsquareapp.mvp.model.api.IJSONLoader
import nitrosrus.colorsquareapp.mvp.model.api.IJSONParser
import nitrosrus.colorsquareapp.util.JSONLoader
import nitrosrus.colorsquareapp.util.JSONParser
import nitrosrus.colorsquareapp.mvp.model.entity.User
import nitrosrus.colorsquareapp.mvp.presenter.contract.UsersContract
import nitrosrus.colorsquareapp.mvp.presenter.list.IUserListPresenter
import nitrosrus.colorsquareapp.mvp.view.list.UserItemView

class UsersPresenter() : BasePresenterImpl<UsersContract.View>(), UsersContract.Presenter {

    private val jsonLoader: IJSONLoader = JSONLoader()
    private val jsonParser: IJSONParser = JSONParser()
    private var listPresenter = UsersListPresenter()
    private lateinit var view: UsersContract.View

    class UsersListPresenter : IUserListPresenter {

        val list = MutableLiveData<MutableList<User>>()

        override fun getLiveData() = list

        override var itemClickListener: ((Int) -> Unit)? = null

        override fun getCount() = list.value?.size ?: -1

        override fun bind(view: UserItemView) {
            view.setName(list.value!![view.pos].name)
            view.setClickListener()
        }
    }

    override fun attachView(view: UsersContract.View) {
        super.attachView(view)
        this.view = view
        Thread { loadData() }.start()
        listPresenter.itemClickListener = { itemClick(it) }
    }

    private fun itemClick(index: Int) {
        try {
            view.nextScreen(listPresenter.list.value!![index].id)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    override fun loadData() {
        listPresenter.list.postValue(jsonParser.parseToUsersList(jsonLoader.getUsers()))
    }

    override fun getListPresenter(): IUserListPresenter {
        return listPresenter
    }
}