package nitrosrus.colorsquareapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import nitrosrus.colorsquareapp.R
import nitrosrus.colorsquareapp.baseMvp.BaseFragment
import nitrosrus.colorsquareapp.databinding.FragmentUsersBinding
import nitrosrus.colorsquareapp.mvp.presenter.UsersPresenter
import nitrosrus.colorsquareapp.mvp.presenter.contract.UsersContract
import nitrosrus.colorsquareapp.mvp.view.ActionBarSettings
import nitrosrus.colorsquareapp.ui.adapter.UserRVAdapter
import nitrosrus.colorsquareapp.ui.replaceFragment

class UsersFragment() :
    BaseFragment<UsersContract.View, UsersContract.Presenter>(R.layout.fragment_users),
    UsersContract.View {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private var adapter: UserRVAdapter? = null
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    override var presenter: UsersContract.Presenter = UsersPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        setActionBarSettings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.getListPresenter().getLiveData().removeObservers(this)
        _binding = null
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserRVAdapter(presenter.getListPresenter())
        binding.rvUsers.adapter = adapter
        presenter.getListPresenter().getLiveData().observe(this, { updateRVAdapter() })
    }

    override fun updateRVAdapter() {
        adapter?.notifyDataSetChanged()
    }

    override fun nextScreen(index: Int) {
        val context = activity as AppCompatActivity
        val photosFragment = PhotosFragment.newInstance(index)
        context.replaceFragment(photosFragment)
    }

    private fun setActionBarSettings() {
        (activity as ActionBarSettings).setUsersScreenSetting()
    }
}