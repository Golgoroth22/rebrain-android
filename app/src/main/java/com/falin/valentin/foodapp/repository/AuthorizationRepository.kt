package com.falin.valentin.foodapp.repository

import com.falin.valentin.foodapp.interactor.AuthorizationDataStorage
import com.falin.valentin.foodapp.interactor.AuthorizationInfoStorage
import com.falin.valentin.foodapp.network.retrofit.pojo.login.LoginRequest
import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import com.falin.valentin.foodapp.network.retrofit.service.LoginService
import com.falin.valentin.foodapp.network.retrofit.service.RetrofitCallback

/**
 * Repository-layer class for work with [AuthorizationFragment].
 *
 */
class AuthorizationRepository(
    private val authStorage: AuthorizationDataStorage,
    private val authInfoStorage: AuthorizationInfoStorage,
    private val authService: LoginService
) {
    /**
     * This method can be called for get info about user authorization status.
     *
     * @return [Boolean]
     */
    fun isUserAuthorized() = authInfoStorage.isUserAuthorized()

    /**
     * This method can be called for setup user token.
     *
     * @param user User token
     */
    fun setUserData(user: UserResponse) = authStorage.setUserAuthorizationData(user)

    /**
     * This method can be called for setup authorization status to true.
     *
     */
    fun setUserAuthorized() = authStorage.setUserAuthorized()

    /**
     * This method can be called for trying login user.
     *
     * @param email User email
     * @param pass User password
     */
    fun tryToSendLoginRequest(
        email: String,
        pass: String,
        onSuccess: (UserResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        authService.login(LoginRequest(email, pass))
            .enqueue(RetrofitCallback<UserResponse>(onSuccess, onFailure))
    }
}