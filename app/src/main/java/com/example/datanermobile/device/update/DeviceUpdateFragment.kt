package com.example.datanermobile.device.update

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
import com.example.datanermobile.databinding.DeviceUpdateFragmentBinding
import com.example.datanermobile.device.network.DeviceUpdate

class DeviceUpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DeviceUpdateFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.device_update_fragment,
            container,
            false
        )

        val arguments = DeviceUpdateFragmentArgs.fromBundle(requireArguments())

        val device = arguments.device

        val deviceUpdateViewModelFactory = DeviceUpdateViewModelFactory(device)

        val deviceUpdateViewModel = ViewModelProvider(
            this,
            deviceUpdateViewModelFactory
        ).get(DeviceUpdateViewModel::class.java)

        binding.deviceUpdateViewModel = deviceUpdateViewModel
        binding.lifecycleOwner = this

        binding.btDeviceUpdate.setOnClickListener {
            deviceUpdateViewModel.onUpdate(
                DeviceUpdate(
                    deviceId = device.deviceId,
                    deviceIdUpdate = binding.etDeviceId.text.toString(),
                    deviceDescription = binding.etDeviceDescription.text.toString(),
                    deviceState = device.deviceState,
                    deviceType = binding.etDeviceType.text.toString(),
                    workplaceId = binding.etWorkplaceId.text.toString().toInt()
                )
            )
        }

        binding.ivDelete.setOnClickListener {
            deviceUpdateViewModel.onDelete(device.deviceId)
        }

        deviceUpdateViewModel.navigateToDevices.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    DeviceUpdateFragmentDirections.actionDeviceUpdateFragmentToDeviceFragment()
                )
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                deviceUpdateViewModel.doneNavigating()
            }

            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        })

        return binding.root
    }

}
