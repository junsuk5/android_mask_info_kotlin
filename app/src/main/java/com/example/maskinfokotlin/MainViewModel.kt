package com.example.maskinfokotlin

import android.annotation.SuppressLint
import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.maskinfokotlin.model.Store
import com.example.maskinfokotlin.repository.MaskService
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val service: MaskService,
    private val locationProvider: FusedLocationProviderClient,
    @Assisted private val savedStateHandle: SavedStateHandle,
    application: Application
) : AndroidViewModel(application) {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    @SuppressLint("MissingPermission")
    fun fetchStoreInfo() {
        // 로딩 시작
        loadingLiveData.value = true

        locationProvider.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    viewModelScope.launch {
                        val storeInfo = service.fetchStoreInfo(location.latitude, location.longitude)
                        itemLiveData.value = storeInfo.stores.filter { store ->
                            store.remain_stat != null
                        }

                        // 로딩 끝
                        loadingLiveData.value = false
                    }
                }
            }
    }
}