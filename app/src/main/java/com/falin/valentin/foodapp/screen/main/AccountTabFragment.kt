package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.utils.Logger

/**
 * A simple [Fragment] subclass.
 * Use the [AccountTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountTabFragment : BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.ACCOUNT_TAB_FRAGMENT

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_tab, container, false)
    }

    companion object {
        fun newInstance() = AccountTabFragment()
    }
}
