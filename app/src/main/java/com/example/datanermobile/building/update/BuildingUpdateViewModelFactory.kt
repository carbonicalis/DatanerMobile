package com.example.datanermobile.building.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.building.network.BuildingDatabaseDao

class BuildingUpdateViewModelFactory(
    private val dataSource: BuildingDatabaseDao,
    private val buildingKey: Int,
    private val building: Building
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuildingUpdateViewModel::class.java)) {
            return BuildingUpdateViewModel(dataSource, buildingKey, building) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
