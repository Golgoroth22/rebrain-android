package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import com.falin.valentin.foodapp.utils.PreferencesHelper
import javax.inject.Inject

/**
 * Interactor-layer class for work with user authorization status.
 *
 */
class AuthorizationStorage @Inject constructor(private val preferencesHelper: PreferencesHelper) :
    AuthorizationDataStorage, AuthorizationInfoStorage {

    override fun isUserAuthorized() = preferencesHelper.isUserAuthorized

    override fun setUsedAuthorized() {
        preferencesHelper.isUserAuthorized = true
    }

    override fun setUserUnauthorized() {
        preferencesHelper.isUserAuthorized = false
    }

    override fun setUserAuthorizationData(user: UserResponse) {
        preferencesHelper.userToken = user.token
        preferencesHelper.userEmail = user.name
    }
}