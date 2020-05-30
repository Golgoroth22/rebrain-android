package com.falin.valentin.foodapp.interactor

/**
 * Interface for work with user storage.
 *
 */
interface UserStorage<T> {
    /**
     * This method can be called for get user email.
     *
     */
    fun getEmail(): String

    /**
     * This method can be called for get user token.
     *
     */
    fun getUserToken(): T
}