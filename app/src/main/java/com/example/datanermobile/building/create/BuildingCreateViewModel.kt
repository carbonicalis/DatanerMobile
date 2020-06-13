package com.example.datanermobile.building.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.building.network.BuildingApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuildingCreateViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToBuildings = MutableLiveData<Boolean?>()
    val navigateToBuildings: LiveData<Boolean?>
        get() = _navigateToBuildings

    private suspend fun insert(building: Building) {
        withContext(Dispatchers.IO) {
//            database.insert(building)
        }
    }

    fun onInsert(building: Building) {
        uiScope.launch {
            insert(building)

            val propertiesDeferred = BuildingApi.retrofitService.createBuildingAsync(building)

            try {
                propertiesDeferred.await()
            } catch (e: Exception) {
                throw e
            }
        }

        onClose()
    }

    fun doneNavigating() {
        _navigateToBuildings.value = null
    }

    fun onClose() {
        _navigateToBuildings.value = true
    }
}

