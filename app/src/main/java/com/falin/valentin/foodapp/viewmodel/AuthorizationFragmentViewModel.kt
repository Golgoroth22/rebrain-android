package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.repository.AuthorizationRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class AuthorizationFragmentViewModel(private val repository: AuthorizationRepository) :
    ViewModel() {

    fun isUserAuth() = repository.isUserAuthorized()

    fun tryToLogin(email: String, pass: String) {
        repository.tryToSendLoginRequest(email, pass)
    }
}