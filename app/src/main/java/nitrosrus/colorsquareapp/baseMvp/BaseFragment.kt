package nitrosrus.colorsquareapp.baseMvp

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment<in V : BaseView, T : BasePresenter<V>>(fragment: Int) : Fragment(), BaseView {

    protected abstract var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }


}