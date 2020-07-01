package com.example.datanermobile.device.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datanermobile.device.network.DeviceApi
import com.example.datanermobile.device.network.DeviceCreate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DeviceCreateViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToDevices = MutableLiveData<Boolean?>()
    val navigateToDevices: LiveData<Boolean?>
        get() = _navigateToDevices

    fun onInsert(device: DeviceCreate) {
        uiScope.launch {
            val propertiesDeferred = DeviceApi.retrofitService.createDeviceAsync(device)
            val getDevices = DeviceApi.retrofitService.getDeviceAsync(device.workplaceId)

            try {
                propertiesDeferred.await()
                getDevices.await()

                DeviceApi.sendRequestToAppDynamics(201)
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

