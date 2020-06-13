package com.example.datanermobile.building.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datanermobile.building.network.Building

class BuildingUpdateViewModelFactory(
    private val building: Building
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuildingUpdateViewModel::class.java)) {
            return BuildingUpdateViewModel(building) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
