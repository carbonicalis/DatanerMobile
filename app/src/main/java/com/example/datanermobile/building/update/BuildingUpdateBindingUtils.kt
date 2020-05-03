package com.example.datanermobile.building.update

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.example.datanermobile.building.network.Building

@BindingAdapter("buildingUpdateName")
fun EditText.buildingUpdateName(item: Building?) {
    item?.let {
        setText(it.name)
    }
}

@BindingAdapter("buildingUpdateZipCode")
fun EditText.buildingUpdateZipCode(item: Building?) {
    item?.let {
        setText(it.zipCode)
    }
}

@BindingAdapter("buildingUpdateCountry")
fun EditText.buildingUpdateCountry(item: Building?) {
    item?.let {
        setText(it.country)
    }
}

@BindingAdapter("buildingUpdateState")
fun EditText.buildingUpdateState(item: Building?) {
    item?.let {
        setText(it.state)
    }
}

@BindingAdapter("buildingUpdateCity")
fun EditText.buildingUpdateCity(item: Building?) {
    item?.let {
        setText(it.city)
    }
}

@BindingAdapter("buildingUpdateAddressType")
fun EditText.buildingUpdateAddressType(item: Building?) {
    item?.let {
        setText(it.addressType)
    }
}

@BindingAdapter("buildingUpdateAddress")
fun EditText.buildingUpdateAddress(item: Building?) {
    item?.let {
        setText(it.address)
    }
}

@BindingAdapter("buildingUpdateAddressNumber")
fun EditText.buildingUpdateAddressNumber(item: Building?) {
    item?.let {
        setText(it.addressNumber.toString())
    }
}

@BindingAdapter("buildingUpdateCompanyId")
fun EditText.buildingUpdateCompanyId(item: Building?) {
    item?.let {
        setText(it.companyId.toString())
    }
}
