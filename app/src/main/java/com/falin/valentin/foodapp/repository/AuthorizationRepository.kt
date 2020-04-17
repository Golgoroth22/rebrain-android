package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.domain.LoginUiResponse
import com.falin.valentin.foodapp.interactor.AuthorizationStorage
import com.falin.valentin.foodapp.network.Constants
import com.falin.valentin.foodapp.network.retrofit.pojo.login.LoginRequest
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
    private val authStorage: AuthorizationStorage,
    private val authService: LoginService
) {
    /**
     * This method can be called for get info about user authorization status.
     *
     * @return [Boolean]
     */
    fun isUserAuthorized() = authStorage.isUserAuthorized()

    /**
     * This method can be called for setup user token.
     *
     * @param user User token
     */
    fun setUserData(user: UserResponse) = authStorage.setUserAuthorizationData(user)

    /**
     * This method can be called for trying login user.
     *
     * @param email User email
     * @param pass User password
     */
    fun tryToSendLoginRequest(
        email: String,
        pass: String,
        onSuccess: (LoginUiResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        authService.login(LoginRequest(email, pass)).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Timber.e("AuthorizationRepository tryToSendLoginRequest onFailure ${t.message}")
                onFailure.invoke(t)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Timber.i("AuthorizationRepository tryToSendLoginRequest onResponse ${response.body()}")
                if (response.code() == Constants.OK) {
                    authStorage.setUsedAuthorized()
                    response.body()?.let { setUserData(it) }
                }
                onSuccess.invoke(
                    LoginUiResponse(response.body(), response.message())
                )
            }
        })
    }
}