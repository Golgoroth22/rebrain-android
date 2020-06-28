package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.AccountTabFragmentViewModelFactoryModule
import com.falin.valentin.foodapp.di.module.AuthorizationStorageModule
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.viewmodel.AccountTabFragmentViewModel
import com.falin.valentin.foodapp.viewmodel.factories.AccountTabFragmentViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [AccountTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountTabFragment : BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.ACCOUNT_TAB_FRAGMENT

    @Inject
    lateinit var factory: AccountTabFragmentViewModelFactory
    private lateinit var viewModel: AccountTabFragmentViewModel

    private lateinit var bottomBarVisibilityListener: (Boolean) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        viewModel = injectViewModel(factory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewFragment()
    }

    private fun addNewFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_account_tab_mainContainer, selectFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun selectFragment(): BaseFragment {
        return if (viewModel.isUserAuthorized()) {
            bottomBarVisibilityListener.invoke(true)
            AccountFragment.newInstance()
        } else {
            bottomBarVisibilityListener.invoke(false)
            AuthorizationFragment.newInstance { addNewFragment() }
        }
    }

    /**
     * This method can be called for set bottombar visibility.
     *
     */
    fun attachBottomBarVisibilityListener(mBottomBarVisibilityListener: (Boolean) -> Unit) {
        bottomBarVisibilityListener = mBottomBarVisibilityListener
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initAccountTabComponent(
            AccountTabFragmentViewModelFactoryModule(),
            AuthorizationStorageModule()
        ).inject(this)
    }

    companion object {
        fun newInstance() = AccountTabFragment()
    }
}
