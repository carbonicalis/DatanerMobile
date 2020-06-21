package com.example.datanermobile.device.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datanermobile.device.network.AllWorkplaceDevices

class DeviceUpdateViewModelFactory(
    private val device: AllWorkplaceDevices
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeviceUpdateViewModel::class.java)) {
            return DeviceUpdateViewModel(device) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
