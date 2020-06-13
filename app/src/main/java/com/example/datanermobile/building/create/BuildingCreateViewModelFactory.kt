package com.example.datanermobile.building.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BuildingCreateViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuildingCreateViewModel::class.java)) {
            return BuildingCreateViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
