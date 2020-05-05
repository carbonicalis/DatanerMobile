package com.example.datanermobile.building

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.building.network.BuildingApi
import com.example.datanermobile.building.network.BuildingDatabaseDao
import com.example.datanermobile.building.network.BuildingRetrofit
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

    private val _buildings = MutableLiveData<List<BuildingRetrofit>>()
    val buildingsRetrofit: LiveData<List<BuildingRetrofit>>
        get() = _buildings

    init {
        onDeleteAll()
//        onInsert(Building())
        getBuildings(1)
//        buildingsRetrofit.value?.forEach {
//            onInsert(
//                Building(
//                    buildingId = it.buildingId,
//                    name = it.name,
//                    country = it.country,
//                    state = it.state,
//                    city = it.city,
//                    addressType = it.addressType,
//                    address = it.address,
//                    addressNumber = it.addressNumber,
//                    zipCode = it.zipCode,
//                    companyId = it.companyId
//                )
//            )
//        }
    }

//    val buildings = database.getAllBuildingsByCompanyId(0)
//    val buildings = BuildingAsync().onGetBuildings(0)
//    val buildings: LiveData<List<Building>> = BuildingAsync().execute("1").get()
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

    fun onBuildingUpdateClicked(id: Int) {
        _navigateToUpdateBuilding.value = id
    }

    fun onBuildingUpdateNavigated() {
        _navigateToUpdateBuilding.value = null
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

    fun onDeleteAll() {
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
//        onDeleteAll()
        viewModelJob.cancel()
    }

    private fun getBuildings(id: Int) {
        uiScope.launch {
            val propertiesDeferred = BuildingApi.retrofitService.getProperties(id)
            println("propertiesDeferred is $propertiesDeferred")
            try {
                println("cheguei no try")
                val listResult = propertiesDeferred.await()
                println("listResult is $listResult")
                listResult.forEach {
                    onInsert(
                        Building(
                            buildingId = it.buildingId,
                            name = it.name,
                            country = it.country,
                            state = it.state,
                            city = it.city,
                            addressType = it.addressType,
                            address = it.address,
                            addressNumber = it.addressNumber,
                            zipCode = it.zipCode,
                            companyId = it.companyId
                        )
                    )
                }
                _buildings.value = listResult
            } catch (e: Exception) {
                println("direto pro catch")
                _buildings.value = ArrayList()
                throw e
            }
        }
    }
}