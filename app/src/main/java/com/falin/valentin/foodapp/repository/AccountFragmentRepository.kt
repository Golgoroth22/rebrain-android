package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.UserDataStorage

/**
 * Repository-layer class for work with [AccountFragment].
 *
 */
class AccountFragmentRepository(private val storage: UserDataStorage) {
    /**
     * This method can be called for get user email.
     *
     * @return User email
     */
    fun getUserEmail() = storage.getEmail()
}