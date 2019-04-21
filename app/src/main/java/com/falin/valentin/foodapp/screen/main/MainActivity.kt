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
        loadSomeFragment(MainTabFragment.newInstance())
        main_activity_custom_bottom_bar.setOnCustomClickListener(object : onClickCustomListener {
            override fun onClick(tabType: MainTabType) {
                when (tabType) {
                    MainTabType.MAIN -> loadSomeFragment(MainTabFragment.newInstance())
                    MainTabType.FAVORITE -> loadSomeFragment(FavoriteTabFragment.newInstance())
                }
            }
        })
    }

    private fun loadSomeFragment(fragment: BaseFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity_frame_layout, fragment)
        transaction.commit()
    }
}