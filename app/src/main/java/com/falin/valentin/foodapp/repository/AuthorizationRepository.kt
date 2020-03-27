package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.Storage
import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import com.falin.valentin.foodapp.network.retrofit.service.LoginService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Repository-layer class for work with [AuthorizationFragment].
 *
 */
class AuthorizationRepository(
    private val authStorage: Storage<Boolean>,
    private val authService: LoginService
) {
    /**
     * This method can be called for get info about user authorization status.
     *
     * @return [Boolean]
     */
    fun isUserAuthorized() = authStorage.getData()

    /**
     * This method can be called for trying login user.
     *
     * @param email User email
     * @param pass User password
     */
    fun tryToSendLoginRequest(email: String, pass: String) {
        authService.login(login = email, password = pass).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Timber.e("AuthorizationRepository tryToSendLoginRequest onFailure ${t.message}")
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Timber.i("AuthorizationRepository tryToSendLoginRequest onResponse ${response.code()} ${response.message()}")
            }
        })
    }
}