package com.falin.valentin.foodapp.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.UserUiResponse
import com.falin.valentin.foodapp.network.Constants
import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import com.falin.valentin.foodapp.repository.AuthorizationRepository
import timber.log.Timber

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class AuthorizationFragmentViewModel(private val repository: AuthorizationRepository) :
    ViewModel() {
    private val mResponseLiveData = MutableLiveData<UserUiResponse>()
    val responseLiveData: LiveData<UserUiResponse> = mResponseLiveData

    fun isUserAuth() = repository.isUserAuthorized()

    fun tryToLogin(email: String, pass: String) {
        mResponseLiveData.postValue(UserUiResponse(isLoading = true))
        if (isEmailAndPasswordValid(email, pass)) {
            repository.tryToSendLoginRequest(
                email,
                pass,
                { response -> receiveSuccessfulResponse(response) },
                { throwable -> receiveFailureResponse(throwable) })
        } else {
            mResponseLiveData.postValue(UserUiResponse(isLoading = false))
        }
    }

    private fun receiveSuccessfulResponse(response: UserResponse) {
        repository.setUserAuthorized()
        repository.setUserData(response)
        mResponseLiveData.postValue(UserUiResponse(response.convert(), isLoading = false))
        Timber.i("AuthorizationFragmentViewModel receiveSuccessfulResponse $response")
    }

    private fun receiveFailureResponse(t: Throwable) {
        mResponseLiveData.postValue(UserUiResponse(isLoading = false, error = t))
        Timber.e("AuthorizationFragmentViewModel receiveFailureResponse ${t.message}")
    }

    fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 4) {
            return true
        }
        return false
    }
}