package com.example.datanermobile.screens.workplace

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.datanermobile.screens.workplace.network.Workplace

@BindingAdapter("floorNumber")
fun TextView.floorNumber(item: Workplace?) {
    item?.let {
        text = item.number.toString()
    }
}

@BindingAdapter("description")
fun TextView.description(item: Workplace?) {
    item?.let {
        text = item.description
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("deviceState")
fun TextView.deviceState(item: Workplace?) {
    item?.let {
        text = "${item.deviceState?.devicesOn}/${item.deviceState?.allDevices}"
    }
}