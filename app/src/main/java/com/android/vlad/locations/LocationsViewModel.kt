package com.android.vlad.locations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.vlad.data.model.Location
import com.android.vlad.data.source.LocationRepository
import com.android.vlad.data.source.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocationsViewModel(private val locationRepository: LocationRepository) : ViewModel() {

    val showLoading = MutableLiveData<Boolean>()
    val locations = MutableLiveData<List<Location>>()
    val showError = MutableLiveData<String>()
    val navigateToDetail = MutableLiveData<String>()

    fun loadLocations() {
        showLoading.value = true

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { locationRepository.getLocations() }

            showLoading.value = false
            when (result) {
                is Result.Success -> locations.value = result.data
                is Result.Error -> showError.value = result.exception.message
            }
        }
    }

    fun locationClicked(imageUrl: String) {
        navigateToDetail.value = imageUrl
    }

}