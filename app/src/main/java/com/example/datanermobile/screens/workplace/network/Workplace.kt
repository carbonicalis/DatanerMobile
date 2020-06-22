package com.example.datanermobile.screens.workplace.network

import android.os.Parcelable

data class WorkplaceUpdate(
    val floorId: Int,
    val description: String,
    val workplaceId: Int
)

data class Workplace(

    val workplaceId: Int,

    val buildingId: Int,

    val number: Int,

    val floorId: Int,

    val description :String,

    val deviceState: AllDeviceState? = null
)

data class AllDeviceState (
    val devicesOn: Int,
    val devicesOff: Int,
    val allDevices: Int
)
