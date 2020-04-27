package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import com.falin.valentin.foodapp.utils.PreferencesHelper
import javax.inject.Inject

/**
 * Interactor-layer class for work with user authorization status.
 *
 */
class AuthorizationStorage @Inject constructor(private val preferencesHelper: PreferencesHelper) {
    /**
     * This method can be called for get info about user authorization status.
     *
     * @return [Boolean]
     */
    fun isUserAuthorized() = preferencesHelper.isUserAuthorized

    /**
     * This method can be called for setup authorization status to true.
     *
     */
    fun setUsedAuthorized() {
        preferencesHelper.isUserAuthorized = true
    }

    /**
     * This method can be called for setup authorization status to false.
     *
     */
    fun setUserUnauthorized() {
        preferencesHelper.isUserAuthorized = false
    }

    /**
     * This method can be called for setup user token.
     *
     * @param token User token
     */
    fun setUserAuthorizationData(user: UserResponse) {
        preferencesHelper.userToken = user.token
        preferencesHelper.userEmail = user.name
    }
}