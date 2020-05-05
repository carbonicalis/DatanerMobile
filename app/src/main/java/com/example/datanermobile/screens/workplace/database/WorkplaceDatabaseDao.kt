package com.example.datanermobile.screens.workplace.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkplaceDatabaseDao {

    @Insert
    fun insert(workplace: Workplace)

    @Update
    fun update(workplace: Workplace)

    @Query("SELECT * FROM workplace_table ORDER BY number ASC")
    fun getAllWorkplaces(): LiveData<List<Workplace>>

    @Query("SELECT number FROM workplace_table ORDER BY number ASC")
    fun getAllFloors(): LiveData<List<Int>>

    @Query("SELECT * FROM workplace_table ORDER BY workplaceId DESC LIMIT 1" )
    fun getLastWorkplace(): Workplace

    @Query("DELETE FROM workplace_table")
    fun clear()

    @Query("DELETE FROM workplace_table WHERE workplaceId = :workpalceId")
    fun deleteWorplace(workpalceId: Int)
}