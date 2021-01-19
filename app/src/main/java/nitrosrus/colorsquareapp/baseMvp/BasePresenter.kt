package nitrosrus.colorsquareapp.baseMvp

interface BasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun detachView()
    fun loadData()
}