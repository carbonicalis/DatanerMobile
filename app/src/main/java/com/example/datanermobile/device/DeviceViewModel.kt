package com.example.datanermobile.device

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datanermobile.device.network.AllWorkplaceDevices
import com.example.datanermobile.device.network.DeviceApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DeviceViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _devices = MutableLiveData<List<AllWorkplaceDevices>>()
    val devices: LiveData<List<AllWorkplaceDevices>>
        get() = _devices

    init {
        getDevices(1)
    }

    fun getDevices(workplaceId: Int) {
        uiScope.launch {
            val propertiesDeferred = DeviceApi.retrofitService.getDeviceAsync(workplaceId)

            try {
                _devices.value = propertiesDeferred.await()
            } catch (e: Exception) {
                _devices.value = ArrayList()
                throw e
            }
        }
    }
}
