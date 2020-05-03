package com.example.datanermobile.building.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Building::class], version = 1, exportSchema = false)
abstract class BuildingDatabase : RoomDatabase() {

    abstract val buildingDatabaseDao: BuildingDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: BuildingDatabase? = null

        fun getInstance(context: Context): BuildingDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BuildingDatabase::class.java,
                        "building_history_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
