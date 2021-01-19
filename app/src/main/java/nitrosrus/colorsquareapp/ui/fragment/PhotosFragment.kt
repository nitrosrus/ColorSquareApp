package nitrosrus.colorsquareapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import nitrosrus.colorsquareapp.R
import nitrosrus.colorsquareapp.baseMvp.BaseFragment
import nitrosrus.colorsquareapp.databinding.FragmentPhotosBinding
import nitrosrus.colorsquareapp.mvp.presenter.PhotosPresenter
import nitrosrus.colorsquareapp.mvp.presenter.contract.PhotosContract
import nitrosrus.colorsquareapp.mvp.view.ActionBarSettings
import nitrosrus.colorsquareapp.ui.adapter.PhotoRVAdapter


class PhotosFragment(index: Int) :
    BaseFragment<PhotosContract.View, PhotosContract.Presenter>(R.layout.fragment_photos),
    PhotosContract.View {

    companion object {
        fun newInstance(index: Int) = PhotosFragment(index)
    }

    private var adapter: PhotoRVAdapter? = null
    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!
    override var presenter: PhotosContract.Presenter = PhotosPresenter(index)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
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
        binding.rvPhotos.layoutManager = LinearLayoutManager(requireContext())
        adapter = PhotoRVAdapter(presenter.getListPresenter(), requireContext())
        binding.rvPhotos.adapter = adapter
        presenter.getListPresenter().getLiveData().observe(this, { updateRVAdapter() })
    }

    override fun updateRVAdapter() {
        adapter?.notifyDataSetChanged()
    }


    private fun setActionBarSettings() {
        (activity as ActionBarSettings).setPhotosScreenSetting()
    }

}