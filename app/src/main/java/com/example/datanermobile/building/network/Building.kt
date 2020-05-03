package com.example.datanermobile.building.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "building")
data class Building(
    @PrimaryKey(autoGenerate = true)
    val buildingId: Int = 0,

    @ColumnInfo(name = "name")
    val name: String = "Test Name",

    @ColumnInfo(name = "country")
    val country: String = "Test Country",

    @ColumnInfo(name = "state")
    val state: String = "Test State",

    @ColumnInfo(name = "city")
    val city: String = "Test City",

    @ColumnInfo(name = "address_type")
    val addressType: String = "Test Address Type",

    @ColumnInfo(name = "address")
    val address: String = "Test Address",

    @ColumnInfo(name = "address_number")
    val addressNumber: Int = 0,

    @ColumnInfo(name = "zip_code")
    val zipCode: String = "Test Zip Code",

    @ColumnInfo(name = "company_id")
    val companyId: Int = 0
)
