package com.falin.valentin.foodapp.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.UserUiResponse
import com.falin.valentin.foodapp.repository.AccountFragmentRepository
import timber.log.Timber

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 * @property emailLiveData display current user email
 * @property responseLiveData display set user avatar request response
 */
class AccountFragmentViewModel(private val repository: AccountFragmentRepository) : ViewModel() {
    private val mEmailLiveData = MutableLiveData<String>()
    val emailLiveData: LiveData<String> = mEmailLiveData
    private val mResponseLiveData = MutableLiveData<UserUiResponse>()
    val responseLiveData: LiveData<UserUiResponse> = mResponseLiveData


    /**
     * This method can be called for send user avatar on server.
     *
     * @param bitmap user avatar bitmap
     */
    fun setAvatar(bitmap: Bitmap) {
        mResponseLiveData.postValue(UserUiResponse(isLoading = true))
        repository.setAvatar(
            bitmap,
            { unit -> receiveSuccessfulResponse(unit) },
            { throwable -> receiveFailureResponse(throwable) })
    }


    private fun receiveSuccessfulResponse(u: Unit) {
        mResponseLiveData.postValue(UserUiResponse(isLoading = false))
        Timber.i("AccountFragmentViewModel receiveSuccessfulResponse $u")
    }

    private fun receiveFailureResponse(t: Throwable) {
        mResponseLiveData.postValue(UserUiResponse(isLoading = false, error = t))
        Timber.e("AccountFragmentViewModel receiveFailureResponse ${t.message}")
    }

    init {
        mEmailLiveData.postValue(repository.getUserEmail())
    }
}