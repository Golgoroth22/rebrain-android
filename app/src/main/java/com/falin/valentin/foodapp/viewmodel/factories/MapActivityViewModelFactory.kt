package com.falin.valentin.foodapp.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.falin.valentin.foodapp.repository.MapActivityRepository
import com.falin.valentin.foodapp.viewmodel.MapActivityViewModel
import javax.inject.Inject

/**
 * [ViewModelProvider.Factory] subclass for creation of [MapActivityViewModel] class.
 *
 */
class MapActivityViewModelFactory @Inject constructor(private val repository: MapActivityRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MapActivityViewModel(repository) as T
    }
}