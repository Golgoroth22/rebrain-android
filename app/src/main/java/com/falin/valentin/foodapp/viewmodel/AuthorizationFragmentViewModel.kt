package com.falin.valentin.foodapp.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.LoginUiResponse
import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import com.falin.valentin.foodapp.repository.AuthorizationRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class AuthorizationFragmentViewModel(private val repository: AuthorizationRepository) :
    ViewModel() {
    private val mResponseLiveData = MutableLiveData<LoginUiResponse>()
    val responseLiveData: LiveData<LoginUiResponse> = mResponseLiveData

    fun isUserAuth() = repository.isUserAuthorized()

    fun tryToLogin(email: String, pass: String) {
        mResponseLiveData.postValue(LoginUiResponse(isLoading = true))
        if (isEmailAndPasswordValid(email, pass)) {
            repository.tryToSendLoginRequest(
                email,
                pass,
                { response -> receiveSuccessfulResponse(response) },
                { throwable -> receiveFailureResponse(throwable) })
        } else {
            mResponseLiveData.postValue(LoginUiResponse(isLoading = false))
        }
    }

    private fun receiveSuccessfulResponse(response: UserResponse) {
        mResponseLiveData.postValue(LoginUiResponse(response, isLoading = false))
    }

    private fun receiveFailureResponse(t: Throwable) {
        mResponseLiveData.postValue(LoginUiResponse(isLoading = false, error = t))
    }

    fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 4) {
            return true
        }
        return false
    }
}