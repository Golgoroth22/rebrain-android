package com.falin.valentin.foodapp.viewmodel

import android.content.ContentResolver
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.domain.UserUiResponse
import com.falin.valentin.foodapp.network.retrofit.pojo.login.UserResponse
import com.falin.valentin.foodapp.repository.AccountFragmentRepository
import java.io.File

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
     * @param parentDir parent directory file
     * @param contentResolver activity content resolver
     */
    fun sendImage(bitmap: Bitmap, parentDir: File, contentResolver: ContentResolver) {
        mResponseLiveData.postValue(UserUiResponse(isLoading = true))
        repository.tryToSendAvatar(
            bitmap,
            parentDir,
            contentResolver,
            { response -> receiveSuccessfulResponse(response) },
            { throwable -> receiveFailureResponse(throwable) })
    }


    private fun receiveSuccessfulResponse(response: UserResponse) {
        mResponseLiveData.postValue(UserUiResponse(response, isLoading = false))
    }

    private fun receiveFailureResponse(t: Throwable) {
        mResponseLiveData.postValue(UserUiResponse(isLoading = false, error = t))
    }

    init {
        mEmailLiveData.postValue(repository.getUserEmail())
    }
}