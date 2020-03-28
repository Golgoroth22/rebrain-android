package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.utils.PreferencesHelper
import javax.inject.Inject

/**
 * Interactor-layer class for work with user data.
 *
 */
class UserDataStorage @Inject constructor(private val preferencesHelper: PreferencesHelper) {
    /**
     * This method can be called for get user email.
     *
     */
    fun getEmail() = preferencesHelper.userEmail

    /**
     * This method can be called for get user token.
     *
     */
    fun userToken() = preferencesHelper.userToken
}