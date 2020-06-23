package com.example.datanermobile.building.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.building.network.BuildingApi
import com.example.datanermobile.building.network.BuildingRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BuildingUpdateViewModel(
    private val buildingRetrofit: BuildingRetrofit
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToBuildings = MutableLiveData<Boolean?>()
    val navigateToBuildings: LiveData<Boolean?>
        get() = _navigateToBuildings

    fun getBuilding() = buildingRetrofit

    fun onUpdate(building: Building) {
        uiScope.launch {
            val propertiesDeferred = BuildingApi.retrofitService.updateBuildingAsync(building)
            println("cheguei no await")

            try {
                println("cheguei no try do await")
                propertiesDeferred.await()
            } catch (e: Exception) {
                println("direto pro catch")
                throw e
            }
        }

        onClose()
    }

    fun onDelete(id: Int) {
        uiScope.launch {
            val propertiesDeferred = BuildingApi.retrofitService.deleteBuildingAsync(id)
            val buildings = BuildingApi.retrofitService.getBuildingAsync(1)

            try {
                propertiesDeferred.await()
                buildings.await()
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
