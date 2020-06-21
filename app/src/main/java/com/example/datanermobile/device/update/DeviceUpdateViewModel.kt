package com.example.datanermobile.device.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datanermobile.device.network.AllWorkplaceDevices
import com.example.datanermobile.device.network.DeviceApi
import com.example.datanermobile.device.network.DeviceUpdate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DeviceUpdateViewModel(
    private val device: AllWorkplaceDevices
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToDevices = MutableLiveData<Boolean?>()
    val navigateToDevices: LiveData<Boolean?>
        get() = _navigateToDevices

    fun getDevice() = device

    fun onUpdate(device: DeviceUpdate) {
        uiScope.launch {
            val propertiesDeferred = DeviceApi.retrofitService.updateDeviceAsync(device)

            try {
                propertiesDeferred.await()
            } catch (e: Exception) {
                throw e
            }
        }

        onClose()
    }

    fun onDelete(deviceId: String) {
        uiScope.launch {
            val propertiesDeferred = DeviceApi.retrofitService.deleteDeviceAsync(deviceId)

            try {
                propertiesDeferred.await()
            } catch (e: Exception) {
                throw e
            }
        }

        onClose()
    }

    fun doneNavigating() {
        _navigateToDevices.value = null
    }

    fun onClose() {
        _navigateToDevices.value = true
    }
}
