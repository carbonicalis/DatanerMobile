package com.example.datanermobile.building

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.datanermobile.building.network.Building

@BindingAdapter("buildingName")
fun TextView.buildingName(item: Building?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("buildingDevices")
fun TextView.buildingDevices(item: Building?) {
    item?.let {
        text = item.name
    }
}
