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

/**
 * [BaseActivity] subclass to work with MainActivity our application and showing it.
 *
 */
class MainActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainTabFragment = MainTabFragment.newInstance()
        val favoriteTabFragment = FavoriteTabFragment.newInstance()
        attachNewFragmentAndDetachOldFragment(favoriteTabFragment, mainTabFragment)
        main_activity_custom_bottom_bar.setOnCustomClickListener(object : onClickCustomListener {
            override fun onClick(tabType: MainTabType) {
                when (tabType) {
                    MainTabType.MAIN -> {
                        attachNewFragmentAndDetachOldFragment(favoriteTabFragment, mainTabFragment)
                    }
                    MainTabType.FAVORITE -> {
                        attachNewFragmentAndDetachOldFragment(mainTabFragment, favoriteTabFragment)
                    }
                }
            }
        })
        initToolbar()
    }

    fun initToolbar() {
        custom_toolbar.title = getString(R.string.app_name)
        setSupportActionBar(custom_toolbar)
    }

    private fun attachNewFragmentAndDetachOldFragment(oldFragment: BaseFragment, newFragment: BaseFragment) {
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
}