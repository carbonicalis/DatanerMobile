package com.example.datanermobile.screens.workplace.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "workplace_table")
data class Workplace(
    @PrimaryKey(autoGenerate = true)
    val workplaceId: Int,
    @ColumnInfo(name = "buildind_id")
    val buildingId: Int,
    @ColumnInfo(name = "number")
    val number: String,
    @ColumnInfo(name = "floor_id")
    val floorId: Int,
    @ColumnInfo(name = "description")
    val description :String,
    @ColumnInfo(name = "device_state")
    val deviceState: String
)