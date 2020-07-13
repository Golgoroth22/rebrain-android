package com.falin.valentin.foodapp.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.screen.custom_view.MainTabType
import com.falin.valentin.foodapp.screen.custom_view.OnClickCustomListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.falin.valentin.foodapp.screen.dialog.ExitDialogFragment
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.viewmodel.MainActivityViewModel
import com.falin.valentin.foodapp.viewmodel.factories.MainActivityViewModelFactory
import kotlin.concurrent.thread

/**
 * [BaseActivity] subclass to work with MainActivity our application and showing it.
 *
 */
class MainActivity : BaseActivity() {
    override val owner: Logger.Owner
        get() = Logger.Owner.MAIN_ACTIVITY

    lateinit var activityViewModel: MainActivityViewModel

    private val handler = Handler(Handler.Callback {
        custom_toolbar.title = "New title text"
        return@Callback false
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityViewModel = ViewModelProviders.of(
            this,
            MainActivityViewModelFactory()
        ).get(MainActivityViewModel::class.java)
        attachFragments()
        main_activity_custom_bottom_bar.setOnCustomClickListener(object : OnClickCustomListener {
            override fun onClick(tabType: MainTabType) {
                when (tabType) {
                    MainTabType.MAIN -> {
                        custom_toolbar.visibility = View.VISIBLE
                        attachNewFragmentAndDetachOldFragment(
                            activityViewModel.mainTabFragment
                        )
                    }
                    MainTabType.FAVORITE -> {
                        custom_toolbar.visibility = View.VISIBLE
                        attachNewFragmentAndDetachOldFragment(
                            activityViewModel.favoriteTabFragment
                        )
                    }
                    MainTabType.ACCOUNT -> {
                        custom_toolbar.visibility = View.GONE
                        attachNewFragmentAndDetachOldFragment(
                            activityViewModel.accountTabFragment
                        )
                    }
                }
            }
        })
        initToolbar()
    }

    override fun onResume() {
        super.onResume()
        changeTitle()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_button -> {
                activityViewModel.updateMenuItem(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        ExitDialogFragment().show(supportFragmentManager, getString(R.string.exit_dialig_tag))
    }

    private fun initToolbar() {
        custom_toolbar.title = getString(R.string.app_name)
        setSupportActionBar(custom_toolbar)
    }

    private fun changeTitle() = thread {
        handler.sendEmptyMessage(1)
    }


    private fun setBottomBarVisibility(isVisible: Boolean) {
        if (isVisible) {
            main_activity_custom_bottom_bar.visibility = View.VISIBLE
            main_activity_line.visibility = View.VISIBLE
        } else {
            main_activity_custom_bottom_bar.visibility = View.GONE
            main_activity_line.visibility = View.GONE
        }
    }

    private fun attachNewFragmentAndDetachOldFragment(newFragment: BaseFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentById((supportFragmentManager.findFragmentById(R.id.main_activity_frame_layout) as BaseFragment).id) != null) {
            transaction.detach(supportFragmentManager.findFragmentById(R.id.main_activity_frame_layout) as BaseFragment)
        }
        transaction.attach(newFragment)
        transaction.commit()
    }

    private fun attachFragments() {
        activityViewModel.accountTabFragment.attachBottomBarVisibilityListener { isVisible ->
            setBottomBarVisibility(isVisible)
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.main_activity_frame_layout, activityViewModel.favoriteTabFragment)
            .detach(activityViewModel.favoriteTabFragment)
            .add(R.id.main_activity_frame_layout, activityViewModel.accountTabFragment)
            .detach(activityViewModel.accountTabFragment)
            .add(R.id.main_activity_frame_layout, activityViewModel.mainTabFragment)
            .attach(activityViewModel.mainTabFragment)
            .commit()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}