package com.example.datanermobile.screens.workplace

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.datanermobile.screens.workplace.database.Floor
import com.example.datanermobile.screens.workplace.database.Workplace
import com.example.datanermobile.screens.workplace.database.WorkplaceDatabaseDao
import kotlinx.coroutines.*

class WorkplaceViewModel(
    val database: WorkplaceDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val workplaces = database.getAllWorkplaces()

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
            insert(workplace)
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
            update(workplace)
        }
    }

    private suspend fun insert(workplace: Workplace) {
        withContext(Dispatchers.IO) {
            database.insert(workplace)

        }
    }

    private suspend fun update(workplace: Workplace) {
        withContext(Dispatchers.IO) {
            database.update(workplace)

        }
    }


}