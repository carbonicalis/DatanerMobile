package com.example.datanermobile.screens.workplace.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Workplace::class], version = 1, exportSchema = false)
abstract class WorkplaceDataBase : RoomDatabase() {

    abstract val workplaceDatabaseDao: WorkplaceDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: WorkplaceDataBase? = null

        fun getInstance(context: Context): WorkplaceDataBase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WorkplaceDataBase::class.java,
                        "workplace_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}