package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.UserDataStorageModule
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.viewmodel.AccountFragmentViewModel
import com.falin.valentin.foodapp.viewmodel.AccountFragmentViewModelFactory
import kotlinx.android.synthetic.main.fragment_account.view.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * A simple [BaseFragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.ACCOUNT_FRAGMENT

    @Inject
    lateinit var factory: AccountFragmentViewModelFactory
    private lateinit var viewModel: AccountFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        viewModel = injectViewModel(factory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_account, container, false)
        initViews(rootView)
        return rootView
    }

    private fun initViews(rootView: View) {
        rootView.fragment_account_pickupPointsButton.setOnClickListener {
            toast("In progress")
        }
        rootView.fragment_account_userNameText.text = viewModel.getUserEmail()
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initAccountFragmentComponent(UserDataStorageModule()).inject(this)
    }

    companion object {
        fun newInstance() = AccountFragment()
    }
}
