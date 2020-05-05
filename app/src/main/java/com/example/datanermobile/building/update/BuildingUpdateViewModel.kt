package com.example.datanermobile.building.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.building.network.BuildingApi
import com.example.datanermobile.building.network.BuildingDatabaseDao
import com.example.datanermobile.building.network.BuildingRetrofit
import com.example.datanermobile.building.network.BuildingRetrofitPut
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

class BuildingUpdateViewModel(
    private val database: BuildingDatabaseDao,
    private val buildingKey: Int = 0
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToBuildings = MutableLiveData<Boolean?>()
    val navigateToBuildings: LiveData<Boolean?>
        get() = _navigateToBuildings

    private val building = MediatorLiveData<Building>()

    //    private val buildingRetrofit = MediatorLiveData<Building>()
    fun getBuilding() = building
//    fun getBuildingRetrofit() = buildingRetrofit

    init {
        building.addSource(database.get(buildingKey), building::setValue)
    }

//    init {
//        buildingRetrofit.addSource(database.get(buildingKey), buildingRetrofit::setValue)
//    }

    private suspend fun update(building: Building) {
        withContext(Dispatchers.IO) {
            database.update(building)
        }
    }

    fun onUpdate(building: Building) {
        uiScope.launch {
            update(building)

            val buildingJson = """
                {
                    "buildingId": "${building.buildingId}",
                    "name": "${building.name}",
                    "country": "${building.country}",
                    "state": "${building.state}",
                    "city": "${building.city}",
                    "addressType": "${building.addressType}",
                    "address": "${building.address}",
                    "addressNumber": "${building.addressNumber}",
                    "zipCode": "${building.zipCode}",
                    "companyId": "${building.companyId}"
                }
            """
            val body = RequestBody.create(
                okhttp3.MediaType
                    .parse("application/json; charset=utf-8"), buildingJson
            )
            println(body.toString())

            val buildingRetrofitPut = BuildingRetrofitPut(
                buildingId = building.buildingId,
                name = building.name,
                country = building.country,
                state = building.state,
                city = building.city,
                addressType = building.addressType,
                address = building.address,
                addressNumber = building.addressNumber,
                zipCode = building.zipCode,
                companyId = building.companyId
            )
//            val propertiesDeferred = BuildingApi.retrofitService.updateBuilding(body)
//            val propertiesDeferred = BuildingApi.retrofitService.updateBuilding(buildingRetrofitPut)
            val propertiesDeferred = BuildingApi.retrofitService.updateBuilding(building)
            println("cheguei no await")
//            BuildingApi.retrofitService.updateBuilding(buildingRetrofitPut).await()

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

    private suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            database.delete(id)
        }
    }

    fun onDelete(id: Int) {
        uiScope.launch {
            delete(id)

            val propertiesDeferred = BuildingApi.retrofitService.deleteBuilding(id)

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
