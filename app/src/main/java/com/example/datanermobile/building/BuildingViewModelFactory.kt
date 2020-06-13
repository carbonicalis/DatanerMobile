package com.example.datanermobile.building

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datanermobile.building.network.BuildingDatabaseDao

class BuildingViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuildingViewModel::class.java)) {
            return BuildingViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
