package com.example.datanermobile.building

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.datanermobile.building.network.BuildingRetrofit

@BindingAdapter("buildingName")
fun TextView.buildingName(item: BuildingRetrofit?) {
    item?.let {
        text = item.name
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("buildingDevices")
fun TextView.buildingDevices(item: BuildingRetrofit?) {
    item?.let {
        text = "${item.deviceState?.devicesOn}/${item.deviceState?.allDevices} devices ativos"
//        text = item.name
    }
}
