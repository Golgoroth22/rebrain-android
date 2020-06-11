package com.falin.valentin.foodapp.network.retrofit.pojo

/**
 * Interface for convert retrofit entities to domain
 *
 */
interface BaseResponse<T> {
    fun convert(): T
}