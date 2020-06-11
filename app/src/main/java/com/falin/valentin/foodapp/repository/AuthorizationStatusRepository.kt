package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.AuthorizationInfoStorage

/**
 * Repository-layer class for work with user authorization status.
 *
 */
class AuthorizationStatusRepository(
    private val authStorage: AuthorizationInfoStorage
) {
    /**
     * This method can be called for get info about user authorization status.
     *
     * @return [Boolean]
     */
    fun isUserAuthorized() = authStorage.isUserAuthorized()

}