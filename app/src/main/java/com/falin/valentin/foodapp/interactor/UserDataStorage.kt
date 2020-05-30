package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.utils.PreferencesHelper
import javax.inject.Inject

/**
 * Interactor-layer class for work with user data.
 *
 */
class UserDataStorage @Inject constructor(private val preferencesHelper: PreferencesHelper) :
    UserStorage<String> {

    override fun getEmail() = preferencesHelper.userEmail

    override fun getUserToken() = preferencesHelper.userToken
}