package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.AuthorizationFragmentViewModelFactoryModule
import com.falin.valentin.foodapp.di.module.interfaces.ServerResponseCallback
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
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
class AuthorizationFragment : BaseFragment(), ServerResponseCallback {
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
        initViews()
        return rootView
    }

    override fun isRequestSuccess(isRequestSuccess: Boolean) {
        if (isRequestSuccess) {
            toast(getString(R.string.fragment_authorization_done_text))
        } else {
            setViewsEnabled()
        }
    }

    private fun initViews() {
        rootView.fragment_authorization_authButton.setOnClickListener {
            val email = rootView.fragment_authorization_emailEditText.text.toString().trim()
            val pass = rootView.fragment_authorization_passwordEditText.text.toString().trim()
            if (isEmailAndPasswordValid(email, pass)) {
                viewModel.tryToLogin(email, pass)
                setViewsDisabled()
            } else {
                toast(getString(R.string.fragment_authorization_login_error_text))
            }
        }
        object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {}

            override fun beforeTextChanged(chars: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(chars: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (chars != null) {
                    rootView.fragment_authorization_authButton.isEnabled = chars.isNotEmpty()
                }
            }
        }.also {
            rootView.fragment_authorization_emailEditText.addTextChangedListener(it)
            rootView.fragment_authorization_passwordEditText.addTextChangedListener(it)
        }
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initAuthComponent(
            AuthorizationFragmentViewModelFactoryModule(this)
        ).inject(this)
    }

    private fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        if (email.contains("@") &&
            email.length > 8 &&
            password.isNotEmpty() &&
            email.contains(".") &&
            !password.contains(" ") &&
            !password.contains("-")
        ) {
            return true
        }
        return false
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
