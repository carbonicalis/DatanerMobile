package com.example.datanermobile.device.create

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.datanermobile.R
import com.example.datanermobile.databinding.DeviceCreateFragmentBinding
import com.example.datanermobile.device.network.DeviceCreate

class DeviceCreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DeviceCreateFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.device_create_fragment,
            container,
            false
        )

        val deviceCreateViewModelFactory = DeviceCreateViewModelFactory()

        val deviceCreateViewModel = ViewModelProvider(
            this,
            deviceCreateViewModelFactory
        ).get(DeviceCreateViewModel::class.java)

        binding.deviceCreateViewModel = deviceCreateViewModel
        binding.lifecycleOwner = this

        binding.btDeviceCreate.setOnClickListener {
            deviceCreateViewModel.onInsert(
                DeviceCreate(
                    deviceId = binding.etDeviceId.text.toString(),
                    deviceDescription = binding.etDeviceDescription.text.toString(),
                    deviceType = binding.etDeviceType.text.toString(),
                    workplaceId = binding.etWorkplaceId.text.toString().toInt()
                )
            )
        }

        deviceCreateViewModel.navigateToDevices.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    DeviceCreateFragmentDirections.actionDeviceCreateFragmentToDeviceFragment()
                )

                deviceCreateViewModel.doneNavigating()
            }

            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        })

        return binding.root
    }

}
