package nitrosrus.colorsquareapp.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import nitrosrus.colorsquareapp.R
import nitrosrus.colorsquareapp.databinding.ActivityMainBinding
import nitrosrus.colorsquareapp.mvp.presenter.MainPresenter
import nitrosrus.colorsquareapp.mvp.presenter.MainPresenterImpl
import nitrosrus.colorsquareapp.mvp.view.ActionBarSettings
import nitrosrus.colorsquareapp.mvp.view.MainView
import nitrosrus.colorsquareapp.ui.fragment.UsersFragment

fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()

}

class MainActivity : AppCompatActivity(), MainView, ActionBarSettings {

    private var presenter: MainPresenter<MainView> = MainPresenterImpl()
    lateinit var binding: ActivityMainBinding
    private lateinit var actionBar: ActionBar
    private lateinit var actionBack: LinearLayout
    private lateinit var actionTitle: TextView
    private lateinit var actionBackTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attachView(this)
    }

    override fun init() {
        replaceFragment(UsersFragment.newInstance())
        actionBar = supportActionBar!!
        actionBar.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.cusom_action_bar)
        }
        actionBackTitle = findViewById(R.id.action_back_title)
        actionBack = findViewById(R.id.action_bar_ll)
        actionTitle = findViewById(R.id.action_bar_title)
        actionBack.setOnClickListener { onBackPressed() }

    }

    override fun setUsersScreenSetting() {
        actionBack.visibility = View.INVISIBLE
        actionTitle.text = getString(R.string.appUsers)
    }

    override fun setPhotosScreenSetting() {
        actionBack.visibility = View.VISIBLE
        actionBackTitle.text = getString(R.string.appUsers)
        actionTitle.text = getString(R.string.appPhoto)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            super.onBackPressed()
        else finish()
    }

}

