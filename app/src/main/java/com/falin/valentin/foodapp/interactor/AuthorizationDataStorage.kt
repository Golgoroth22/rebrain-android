package com.falin.valentin.foodapp.interactor

import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse

/**
 * Interface for work with user authorization storage.
 *
 */
interface AuthorizationDataStorage {

    /**
     * This method can be called for setup authorization status to true.
     *
     */
    fun setUserAuthorized()

    /**
     * This method can be called for setup authorization status to false.
     *
     */
    fun setUserUnauthorized()

    /**
     * This method can be called for setup user token and email.
     *
     * @param user User entity
     */
    fun setUserAuthorizationData(user: UserResponse)
}