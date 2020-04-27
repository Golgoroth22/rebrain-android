package com.falin.valentin.foodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.repository.AccountFragmentRepository

/**
 * [ViewModel] subclass for work with model data and showing it.
 *
 */
class AccountFragmentViewModel(private val repository: AccountFragmentRepository) : ViewModel() {
    private val mEmailLiveData = MutableLiveData<String>()
    val emailLiveData: LiveData<String> = mEmailLiveData

    init {
        mEmailLiveData.postValue(repository.getUserEmail())
    }
}