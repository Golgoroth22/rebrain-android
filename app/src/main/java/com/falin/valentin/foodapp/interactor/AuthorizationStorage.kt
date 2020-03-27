package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.utils.PreferencesHelper
import javax.inject.Inject

/**
 * Interactor-layer class for work with user authorization status.
 *
 */
class AuthorizationStorage @Inject constructor(private val preferencesHelper: PreferencesHelper) :
    Storage<Boolean> {
    override fun getData() = preferencesHelper.isUserAuthorized

    override fun setData() {
        preferencesHelper.isUserAuthorized = true
    }
}