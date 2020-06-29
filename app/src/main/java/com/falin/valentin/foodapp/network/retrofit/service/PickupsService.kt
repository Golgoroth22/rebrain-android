package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.PickupResponse
import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.PickupsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Retrofit interface for get pickups.
 *
 */
interface PickupsService {
    @GET("pickups/")
    fun getPickups(@Header("x-access-token") token: String): Call<List<PickupResponse>>
}