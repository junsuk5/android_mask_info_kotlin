package com.example.maskinfokotlin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maskinfokotlin.model.Store
import com.example.maskinfokotlin.repository.MaskService
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val service: MaskService,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    init {
        fetchStoreInfo()
    }

    fun fetchStoreInfo() {
        // 로딩 시작
        loadingLiveData.value = true

        viewModelScope.launch {
            val storeInfo = service.fetchStoreInfo(37.188078, 127.043002)
            itemLiveData.value = storeInfo.stores

            // 로딩 끝
            loadingLiveData.value = false
        }
    }
}