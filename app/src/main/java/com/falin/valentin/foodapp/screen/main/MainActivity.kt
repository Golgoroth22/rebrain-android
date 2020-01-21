package com.falin.valentin.foodapp.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.screen.custom_view.MainTabType
import com.falin.valentin.foodapp.screen.custom_view.onClickCustomListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.falin.valentin.foodapp.screen.dialog.ExitDialogFragment
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.viewmodel.MainActivityViewModel
import com.falin.valentin.foodapp.viewmodel.MainActivityViewModelFactory


/**
 * [BaseActivity] subclass to work with MainActivity our application and showing it.
 *
 */
class MainActivity : BaseActivity() {
    override val owner: Logger.Owner
        get() = Logger.Owner.MAIN_ACTIVITY

    lateinit var activityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityViewModel = ViewModelProviders.of(
            this,
            MainActivityViewModelFactory()
        ).get(MainActivityViewModel::class.java)
        attachFragments()
        main_activity_custom_bottom_bar.setOnCustomClickListener(object : onClickCustomListener {
            override fun onClick(tabType: MainTabType) {
                when (tabType) {
                    MainTabType.MAIN -> {
                        attachNewFragmentAndDetachOldFragment(
                            activityViewModel.favoriteTabFragment,
                            activityViewModel.mainTabFragment
                        )
                    }
                    MainTabType.FAVORITE -> {
                        attachNewFragmentAndDetachOldFragment(
                            activityViewModel.mainTabFragment,
                            activityViewModel.favoriteTabFragment
                        )
                    }
                }
            }
        })
        initToolbar()
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

    private fun attachNewFragmentAndDetachOldFragment(
        oldFragment: BaseFragment,
        newFragment: BaseFragment
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentById(oldFragment.id) != null) {
            transaction.detach(oldFragment)
        }
        if (supportFragmentManager.findFragmentById(newFragment.id) == null) {
            transaction.add(R.id.main_activity_frame_layout, newFragment)
        } else {
            transaction.attach(newFragment)
        }
        transaction.commit()
    }

    private fun attachFragments() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.main_activity_frame_layout, activityViewModel.mainTabFragment)
            .add(R.id.main_activity_frame_layout, activityViewModel.favoriteTabFragment)
            .detach(activityViewModel.favoriteTabFragment)
            .attach(activityViewModel.mainTabFragment)
            .commit()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}