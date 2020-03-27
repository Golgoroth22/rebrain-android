package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.Storage

/**
 * Repository-layer class for work with [AccountTabFragment].
 *
 */
class AccountTabRepository(
    private val authStorage: Storage<Boolean>
) {
    /**
     * This method can be called for get info about user authorization status.
     *
     * @return [Boolean]
     */
    fun isUserAuthorized() = authStorage.getData()
}