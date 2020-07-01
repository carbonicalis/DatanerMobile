package com.example.datanermobile.building

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.building.network.BuildingApi
import com.example.datanermobile.building.network.BuildingRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BuildingViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _buildings = MutableLiveData<List<BuildingRetrofit>>()
    val buildingsRetrofit: LiveData<List<BuildingRetrofit>>
        get() = _buildings

    init {
        getBuildings(1)
    }

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

    override fun onCleared() {
        super.onCleared()
//        onDeleteAll()
        viewModelJob.cancel()
    }

    fun getBuildings(companyId: Int) {
        uiScope.launch {
            val propertiesDeferred = BuildingApi.retrofitService.getBuildingAsync(companyId)
            println("propertiesDeferred is $propertiesDeferred")
            try {
                println("cheguei no try")
                val listResult = propertiesDeferred.await()
                println("listResult is $listResult")

                BuildingApi.sendRequestToAppDynamics(200)

                _buildings.value = listResult
            } catch (e: Exception) {
                println("direto pro catch")
                _buildings.value = ArrayList()
                throw e
            }
        }
    }
}