package com.example.datanermobile.device

import android.widget.Switch
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.datanermobile.device.network.AllWorkplaceDevices

@BindingAdapter("deviceName")
fun TextView.deviceName(item: AllWorkplaceDevices?) {
    item?.let {
        text = item.deviceDescription
    }
}

@BindingAdapter("deviceState")
fun Switch.deviceState(item: AllWorkplaceDevices?) {
    item?.let {
        isChecked = it.deviceState
    }
}
