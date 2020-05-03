package com.example.datanermobile.building

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.building.network.BuildingDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuildingViewModel(
    private val database: BuildingDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var building = MutableLiveData<Building?>()

//    init {
//        onDeleteAll()
//        onInsert(Building())
//    }

    val buildings = database.getAllBuildingsByCompanyId(0)
//    val buildings = listOf(Building(name = "Teste"), Building(name = "Test"))

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    private val _navigateToCreateBuilding = MutableLiveData<Building>()
    val navigateToCreateBuilding: LiveData<Building>
        get() = _navigateToCreateBuilding

    fun doneNavigating() {
        _navigateToCreateBuilding.value = null
    }

    private val _navigateToUpdateBuilding = MutableLiveData<Int>()
    val navigateToUpdateBuilding
        get() = _navigateToUpdateBuilding

    fun onBuildingUpdateClicked(id: Int){
        _navigateToUpdateBuilding.value = id
    }

    fun onBuildingUpdateNavigated() {
        _navigateToUpdateBuilding.value = null
    }

    private suspend fun clear(id: Int) {
        withContext(Dispatchers.IO) {
            database.clear(id)
        }
    }

    private suspend fun insert(building: Building) {
        withContext(Dispatchers.IO) {
            database.insert(building)
        }
    }

    fun onInsert(building: Building) {
        uiScope.launch {
            insert(building)
        }
    }

    private fun onDeleteAll() {
        uiScope.launch {
            deleteAll()
        }
    }

    private suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            database.deleteAll()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}