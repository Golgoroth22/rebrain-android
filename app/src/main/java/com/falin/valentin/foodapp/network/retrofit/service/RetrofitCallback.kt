package com.falin.valentin.foodapp.network.retrofit.service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.IllegalStateException

/**
 * [Callback]-subclass for simple work with requests.
 *
 */
class RetrofitCallback<T>(
    val onSuccess: (T) -> Unit,
    val onError: (Throwable) -> Unit
) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        Timber.i("RetrofitCallback onFailure message:${t.localizedMessage}")
        onError(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        Timber.i("RetrofitCallback onResponse code:${response.code()}")
        val body = response.body()
        if (response.isSuccessful && body != null) {
            onSuccess(body)
        } else {
            onError(IllegalStateException("Response must not be null: $response"))
        }
    }
}