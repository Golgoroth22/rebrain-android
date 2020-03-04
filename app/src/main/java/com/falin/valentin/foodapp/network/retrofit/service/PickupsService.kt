package com.falin.valentin.foodapp.network.retrofit.service

import com.falin.valentin.foodapp.network.retrofit.pojo.pickups.Pickups
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit interface for get pickups.
 *
 */
interface PickupsService {
    @GET("pickups")
    fun getPickups(): Call<Pickups>
}