package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.AuthorizationFragmentViewModelFactoryModule
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.utils.setOnTextChanged
import com.falin.valentin.foodapp.viewmodel.AuthorizationFragmentViewModel
import com.falin.valentin.foodapp.viewmodel.factories.AuthorizationFragmentViewModelFactory
import kotlinx.android.synthetic.main.fragment_authorization.*
import kotlinx.android.synthetic.main.fragment_authorization.view.*
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [BaseFragment] subclass.
 * Use the [AuthorizationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthorizationFragment(private val successfulAuthorizationListener: () -> Unit) :
    BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.AUTH_FRAGMENT

    @Inject
    lateinit var viewModelFactory: AuthorizationFragmentViewModelFactory
    private lateinit var viewModel: AuthorizationFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_authorization, container, false)
        initLiveData()
        initListeners(rootView)
        return rootView
    }

    private fun initLiveData() {
        viewModel.responseLiveData.observe(this, Observer { uiResponse ->
            Timber.i("AuthorizationFragment responseLiveData $uiResponse")
            if (uiResponse.data != null) {
                toast(getString(R.string.fragment_authorization_done_text))
                successfulAuthorizationListener.invoke()
            }
            if (uiResponse.error != null) {
                toast(uiResponse.error.localizedMessage)
            }
            if (uiResponse.isLoading) {
                setViewsDisabled()
            } else {
                setViewsEnabled()
            }
        })
    }

    private fun initListeners(rootView: View) {
        rootView.fragment_authorization_authButton.setOnClickListener {
            val email = this.fragment_authorization_emailEditText.text.toString().trim()
            val pass = this.fragment_authorization_passwordEditText.text.toString().trim()
            viewModel.tryToLogin(email, pass)
        }
        rootView.fragment_authorization_emailEditText.setOnTextChanged {
            rootView.fragment_authorization_authButton.isEnabled =
                viewModel.isEmailAndPasswordValid(
                    rootView.fragment_authorization_emailEditText.text.toString().trim(),
                    rootView.fragment_authorization_passwordEditText.text.toString().trim()
                )
        }
        rootView.fragment_authorization_passwordEditText.setOnTextChanged {
            rootView.fragment_authorization_authButton.isEnabled =
                viewModel.isEmailAndPasswordValid(
                    rootView.fragment_authorization_emailEditText.text.toString().trim(),
                    rootView.fragment_authorization_passwordEditText.text.toString().trim()
                )
        }
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initAuthComponent(
            AuthorizationFragmentViewModelFactoryModule()
        ).inject(this)
    }

    private fun setViewsEnabled() {
        fragment_authorization_authButton.isEnabled = true
        fragment_authorization_emailEditText.isEnabled = true
        fragment_authorization_passwordEditText.isEnabled = true
        fragment_authorization_loadingProgress.visibility = View.GONE
    }

    private fun setViewsDisabled() {
        fragment_authorization_authButton.isEnabled = false
        fragment_authorization_emailEditText.isEnabled = false
        fragment_authorization_passwordEditText.isEnabled = false
        fragment_authorization_loadingProgress.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance(successfulAuthorizationListener: () -> Unit) =
            AuthorizationFragment(successfulAuthorizationListener)
    }
}
