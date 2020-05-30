package com.falin.valentin.foodapp.interactor

/**
 * Interface for work with user authorization storage.
 *
 */
interface AuthorizationInfoStorage {
    /**
     * This method can be called for get info about user authorization status.
     *
     * @return [Boolean]
     */
    fun isUserAuthorized(): Boolean
}