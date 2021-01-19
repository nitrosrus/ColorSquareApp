package nitrosrus.colorsquareapp.baseMvp

open class BasePresenterImpl<V : BaseView> : BasePresenter<V> {

    private var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    override fun loadData() {

    }
}