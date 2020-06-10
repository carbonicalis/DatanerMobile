package com.example.datanermobile.screens.workplace

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datanermobile.screens.workplace.network.Floor
import com.example.datanermobile.screens.workplace.network.Workplace
import kotlinx.coroutines.*

class WorkplaceViewModel(

    application: Application
) : AndroidViewModel(application) {

    val _workplaces = MutableLiveData<List<Workplace>>()
    val workplaces: LiveData<List<Workplace>>
        get() = _workplaces

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var floors = mutableListOf<Floor>()

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


    fun newFloor(buildingId: Int, floorNumber: String) {

        val floor = Floor(buildingId, floorNumber)
        if (floors.contains(floor)) {
            _showSnackbarEvent.value = true
        } else {
            floors.add(floor)
        }
    }

    fun newWorkplace(buildingId: Int, floorNumber: Int, workplaceName: String) {
        uiScope.launch {
            val workplace =
                Workplace(0, buildingId, floorNumber, floorNumber.toInt(), workplaceName, "0/0")
            postWorkplace(workplace)
        }

    }

    fun updateWorkplace(
        buildingId: Int,
        workplaceId: Int,
        workplaceName: String,
        floorNumber: Int
    ) {
        uiScope.launch {
            val workplace =
                Workplace(
                    workplaceId,
                    buildingId,
                    floorNumber,
                    floorNumber,
                    workplaceName,
                    "0/0"
                )
            putWorkplace(workplace)
        }
    }

    private suspend fun postWorkplace(workplace: Workplace) {
        withContext(Dispatchers.IO) {
//            TODO CHAMAR POST

        }
    }

    private suspend fun putWorkplace(workplace: Workplace) {
        withContext(Dispatchers.IO) {
//          TODO CHAMAR PUT

        }
    }

     fun getWorkplaces(){
         //          TODO CHAMAR GET

         _workplaces.value = listOf<Workplace>(
            Workplace(1,1,1,1,"TESTE","01/20"),
            Workplace(2,2,2,2,"TESTE 2","01/20")
        )
    }


}