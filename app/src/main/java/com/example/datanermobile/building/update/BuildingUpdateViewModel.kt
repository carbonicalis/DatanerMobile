package com.example.datanermobile.building.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.building.network.BuildingDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuildingUpdateViewModel (
    private val database: BuildingDatabaseDao,
    private val buildingKey: Int = 0
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToBuildings = MutableLiveData<Boolean?>()
    val navigateToBuildings: LiveData<Boolean?>
        get() = _navigateToBuildings

    private val building = MediatorLiveData<Building>()
    fun getBuilding() = building

    init {
        building.addSource(database.get(buildingKey), building::setValue)
    }

    private suspend fun update(building: Building) {
        withContext(Dispatchers.IO) {
            database.update(building)
        }
    }

    fun onUpdate(building: Building) {
        uiScope.launch {
            update(building)
        }

        _navigateToBuildings.value = true
    }

    fun doneNavigating() {
        _navigateToBuildings.value = null
    }
}
