package com.falin.valentin.foodapp.interactor

/**
 * Interface for work with storages.
 *
 */
interface Storage<T> {
    /**
     * This method can be called for get some data.
     *
     */
    fun getData(): T

    /**
     * This method can be called for set some data.
     *
     */
    fun setData()
}