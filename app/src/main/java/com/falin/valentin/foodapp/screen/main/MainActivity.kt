package com.falin.valentin.foodapp.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.screen.custom_view.MainTabType
import com.falin.valentin.foodapp.screen.custom_view.onClickCustomListener
import kotlinx.android.synthetic.main.activity_main.*

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
        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.main_activity_frame_layout, favoriteTabFragment)
            .detach(favoriteTabFragment)
            .add(R.id.main_activity_frame_layout, mainTabFragment)
            .commit()
        main_activity_custom_bottom_bar.setOnCustomClickListener(object : onClickCustomListener {
            override fun onClick(tabType: MainTabType) {
                when (tabType) {
                    MainTabType.MAIN -> {
                        attachNewFragment(favoriteTabFragment, mainTabFragment)
                    }
                    MainTabType.FAVORITE -> {
                        attachNewFragment(mainTabFragment, favoriteTabFragment)
                    }
                }
            }
        })
    }

    private fun attachNewFragment(oldFragment: BaseFragment, newFragment: BaseFragment) {
        if (!oldFragment.isDetached) {
            val transaction = supportFragmentManager.beginTransaction()
                .detach(oldFragment)
                .attach(newFragment)
                .commit()
        }
    }
}