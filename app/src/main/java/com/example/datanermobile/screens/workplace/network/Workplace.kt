package com.example.datanermobile.screens.workplace.network

import android.os.Parcelable


data class Workplace(

    val workplaceId: Int,

    val buildingId: Int,

    val number: Int,

    val floorId: Int,

    val description :String,

    val deviceState: String
)


