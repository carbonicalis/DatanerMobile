package com.example.datanermobile.screens.workplace

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datanermobile.screens.workplace.database.WorkplaceDatabaseDao

class WorkplaceViewModelFactory(
    private val dataSource: WorkplaceDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkplaceViewModel::class.java)) {
            return WorkplaceViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}