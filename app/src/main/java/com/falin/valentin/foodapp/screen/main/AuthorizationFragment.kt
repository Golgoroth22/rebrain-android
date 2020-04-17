package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
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
import com.falin.valentin.foodapp.viewmodel.AuthorizationFragmentViewModelFactory
import kotlinx.android.synthetic.main.fragment_authorization.view.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * A simple [BaseFragment] subclass.
 * Use the [AuthorizationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthorizationFragment : BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.AUTH_FRAGMENT

    @Inject
    lateinit var viewModelFactory: AuthorizationFragmentViewModelFactory
    private lateinit var viewModel: AuthorizationFragmentViewModel

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_authorization, container, false)
        initLiveData()
        initListeners()
        return rootView
    }

    private fun initLiveData() {
        viewModel.loadingLiveData.observe(this, Observer {
            if (it) {
                toast(getString(R.string.fragment_authorization_done_text))
            } else {
                setViewsEnabled()
            }
        })
        viewModel.messageResponseLiveData.observe(this, Observer {
            toast(it)
        })
    }

    private fun initListeners() {
        rootView.fragment_authorization_authButton.setOnClickListener {
            val email = rootView.fragment_authorization_emailEditText.text.toString().trim()
            val pass = rootView.fragment_authorization_passwordEditText.text.toString().trim()
            viewModel.tryToLogin(email, pass)
            setViewsDisabled()
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
        rootView.fragment_authorization_authButton.isEnabled = true
        rootView.fragment_authorization_emailEditText.isEnabled = true
        rootView.fragment_authorization_passwordEditText.isEnabled = true
        rootView.fragment_authorization_loadingProgress.visibility = View.GONE
    }

    private fun setViewsDisabled() {
        rootView.fragment_authorization_authButton.isEnabled = false
        rootView.fragment_authorization_emailEditText.isEnabled = false
        rootView.fragment_authorization_passwordEditText.isEnabled = false
        rootView.fragment_authorization_loadingProgress.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = AuthorizationFragment()
    }
}
