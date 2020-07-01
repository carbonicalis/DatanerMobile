package com.example.datanermobile.device.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DeviceCreateViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeviceCreateViewModel::class.java)) {
            return DeviceCreateViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
