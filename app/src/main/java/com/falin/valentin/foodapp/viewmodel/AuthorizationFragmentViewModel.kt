package com.falin.valentin.foodapp.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.LoginUiResponse
import com.falin.valentin.foodapp.repository.AuthorizationRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class AuthorizationFragmentViewModel(private val repository: AuthorizationRepository) :
    ViewModel() {
    val messageResponseLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun isUserAuth() = repository.isUserAuthorized()

    fun tryToLogin(email: String, pass: String) {
        if (isEmailAndPasswordValid(email, pass)) {
            repository.tryToSendLoginRequest(
                email,
                pass,
                { response -> receiveSuccessfulResponse(response) },
                { throwable -> receiveFailureResponse(throwable) })
        } else {
            loadingLiveData.postValue(false)
        }
    }

    private fun receiveSuccessfulResponse(response: LoginUiResponse) {
        messageResponseLiveData.postValue(response.message)
        loadingLiveData.postValue(false)
    }

    private fun receiveFailureResponse(t: Throwable) {
        messageResponseLiveData.postValue(t.localizedMessage)
        loadingLiveData.postValue(false)
    }

    fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 4) {
            return true
        }
        return false
    }
}