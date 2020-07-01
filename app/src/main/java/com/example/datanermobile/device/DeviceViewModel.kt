package com.example.datanermobile.device

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datanermobile.device.network.AllWorkplaceDevices
import com.example.datanermobile.device.network.DeviceApi
import com.example.datanermobile.device.network.DeviceStateUpdateRequestDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DeviceViewModel(
    application: Application,
    val workplaceId: Int
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _devices = MutableLiveData<List<AllWorkplaceDevices>>()
    val devices: LiveData<List<AllWorkplaceDevices>>
        get() = _devices

    init {
        getDevices(workplaceId)
    }

    fun getDevices(workplaceId: Int) {
        uiScope.launch {
            val propertiesDeferred = DeviceApi.retrofitService.getDeviceAsync(workplaceId)

            try {
                DeviceApi.sendRequestToAppDynamics(200)

                _devices.value = propertiesDeferred.await()
            } catch (e: Exception) {
                _devices.value = ArrayList()
                throw e
            }
        }
    }

    fun updateDeviceUpdate(deviceId: String, deviceState: Boolean) {
        uiScope.launch {
            val propertiesDeferred =
                DeviceApi.retrofitService.updateDeviceStateAsync(DeviceStateUpdateRequestDto(
                    deviceId,
                    deviceState
                ))

            try {
                propertiesDeferred.await()
                getDevices(workplaceId)

                DeviceApi.sendRequestToAppDynamics(200)
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
