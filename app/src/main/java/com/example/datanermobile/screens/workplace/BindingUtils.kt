package com.example.datanermobile.screens.workplace

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.datanermobile.screens.workplace.database.Workplace

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

@BindingAdapter("deviceState")
fun TextView.deviceState(item: Workplace?) {
    item?.let {
        text = item.deviceState
    }
}