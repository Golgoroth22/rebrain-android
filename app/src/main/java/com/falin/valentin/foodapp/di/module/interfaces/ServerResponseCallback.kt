package com.falin.valentin.foodapp.di.module.interfaces


/**
 * Interface for handle server callbacks.
 *
 */
interface ServerResponseCallback {
    /**
     * This method need be called after server answer received.
     *
     * @param isRequestSuccess True is success answer. And false is request failed
     */
    fun isRequestSuccess(isRequestSuccess: Boolean)
}