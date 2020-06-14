package com.example.datanermobile.building

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.datanermobile.building.network.BuildingRetrofit

@BindingAdapter("buildingName")
fun TextView.buildingName(item: BuildingRetrofit?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("buildingDevices")
fun TextView.buildingDevices(item: BuildingRetrofit?) {
    item?.let {
        text = item.name
    }
}
