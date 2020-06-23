package com.example.datanermobile.screens.workplace

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datanermobile.screens.workplace.network.*
import kotlinx.coroutines.*
import retrofit2.http.PUT
import kotlin.math.floor

class WorkplaceViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _workplaces = MutableLiveData<List<Workplace>>()
    val workplaces: LiveData<List<Workplace>>
        get() = _workplaces

    private var _floors = MutableLiveData<List<Floor>>()
    val floors: LiveData<List<Floor>>
        get() = _floors

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _showNewFloorDialog = MutableLiveData<Boolean>()
    val showNewFloorDialog: LiveData<Boolean>
        get() = _showNewFloorDialog

    private val _showNewWorkplaceDialog = MutableLiveData<Boolean>()
    val showNewWorkplaceDialog: LiveData<Boolean>
        get() = _showNewWorkplaceDialog

    private val _showEditWorkplaceDialog = MutableLiveData<Int>()
    val showEditWorkplaceDialog: LiveData<Int>
        get() = _showEditWorkplaceDialog

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun onClickNewFloor() {
        _showNewFloorDialog.value = true
    }

    fun doneCreatingFloor() {
        _showNewFloorDialog.value = false
    }

    fun onClickNewWorkplace() {
        _showNewWorkplaceDialog.value = true
    }

    fun doneCreatingWorkplace() {
        _showNewWorkplaceDialog.value = false
    }


    fun newFloor(buildingId: Int, floorNumber: Int) {
        uiScope.launch {

            postFloor(buildingId, floorNumber)
        }
        getFloors(buildingId)
    }

    fun getFloorId(floorNumber: Int): Int {
        val floors = _floors.value
        var floorId:Int = 0

        floors?.forEach {
            if(it.number == floorNumber){
                floorId = it.floorId
                return floorId
            }
        }
       return floorId
    }

    fun newWorkplace(floorNumber: Int, workplaceName: String, buildingId: Int) {

        val floorId = getFloorId(floorNumber)
        uiScope.launch {
            val workplace =
                WorkplaceRequest(floorId, workplaceName)
            postWorkplace(workplace, buildingId)
        }

    }

    fun updateWorkplace(
        buildingId: Int,
        workplaceId: Int,
        workplaceName: String,
        floorNumber: Int,
        floorId: Int
    ) {
        uiScope.launch {
            val workplace = WorkplaceUpdate(
                floorId,
                workplaceName,
                workplaceId
            )

            putWorkplace(workplace, buildingId)
        }
    }

    private suspend fun postFloor(buildingId: Int, floorNumber: Int) {
        withContext(Dispatchers.IO) {
            val propertiesDeferred =
                FloorApi.retrofitService.createFloorAsync(FloorRequest(buildingId, floorNumber))

            try {
                propertiesDeferred.await()
            } catch (e: Exception) {
                throw e
            }

            getFloors(buildingId)

        }
    }

    private suspend fun postWorkplace(workplaceRequest: WorkplaceRequest, buildingId: Int) {
        withContext(Dispatchers.IO) {
            val propertiesDeferred =
                WorkplaceApi.retrofitService.createWorkplaceAsync(workplaceRequest)
            propertiesDeferred.await()
            getWorkplaces(buildingId)

        }
    }

    private suspend fun putWorkplace(workplace: WorkplaceUpdate, buildingId: Int) {
        withContext(Dispatchers.IO) {
            val propertiesDeferred =
                WorkplaceApi.retrofitService.updateWorkplaceAsync(workplace)

            try {
                propertiesDeferred.await()
            } catch (e: Exception) {
                throw e
            }

            getWorkplaces(buildingId)

        }
    }

    fun getWorkplaces(buildingId: Int) {

        uiScope.launch {
            val propertiesDeferred = FloorApi.retrofitService.getWorkplacesAsync(buildingId)

            val listResult = propertiesDeferred.await()
            _workplaces.value = listResult

//         _workplaces.value = listOf<Workplace>(
//            Workplace(1,1,1,1,"TESTE","01/20"),
//            Workplace(2,2,2,2,"TESTE 2","01/20")
//        )
        }
    }

    fun getFloors(buildingId: Int) {

        uiScope.launch {
            val propertiesDeferred = FloorApi.retrofitService.getFloorsAsync(buildingId)

            val listResult = propertiesDeferred.await()
            println(listResult)
            _floors.value = listResult
        }
    }

}