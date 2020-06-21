package com.example.datanermobile.device.update

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.datanermobile.device.network.AllWorkplaceDevices

@BindingAdapter("deviceId")
fun TextView.deviceId(item: AllWorkplaceDevices?) {
    item?.let {
        text = item.deviceId
    }
}

@BindingAdapter("deviceName")
fun TextView.deviceName(item: AllWorkplaceDevices?) {
    item?.let {
        text = item.deviceDescription
    }
}

@BindingAdapter("deviceType")
fun TextView.deviceType(item: AllWorkplaceDevices?) {
    item?.let {
        text = item.deviceType
    }
}

@BindingAdapter("deviceWorkplace")
fun TextView.deviceWorkplace(item: AllWorkplaceDevices?) {
    item?.let {
        text = item.workplaceId.toString()
    }
}
