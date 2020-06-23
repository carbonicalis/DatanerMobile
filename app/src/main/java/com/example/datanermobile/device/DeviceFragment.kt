package com.example.datanermobile.device

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.datanermobile.R
import com.example.datanermobile.databinding.DeviceFragmentBinding
import kotlinx.android.synthetic.main.list_item_device.switch_state

class DeviceFragment : Fragment() {

    private lateinit var deviceViewModel: DeviceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val workplaceId = requireActivity().intent.getIntExtra(getString(R.string.workplaceId), 0)

        val binding: DeviceFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.device_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val deviceFactory = DeviceViewModelFactory(application, workplaceId)

        deviceViewModel = ViewModelProvider(
            this,
            deviceFactory
        ).get(DeviceViewModel::class.java)

        binding.deviceViewModel = deviceViewModel
        binding.lifecycleOwner = this

        val adapter = DeviceAdapter(
            DeviceListener { device ->

                findNavController().navigate(
                    DeviceFragmentDirections
                        .actionDeviceFragmentToDeviceUpdateFragment(device)
                )
            }, DeviceStateListener { deviceId, deviceState ->
                
                deviceViewModel.updateDeviceUpdate(deviceId, deviceState.not())
            })

        binding.btNewDevice.setOnClickListener {
            findNavController().navigate(
                DeviceFragmentDirections
                    .actionDeviceFragmentToDeviceCreateFragment(workplaceId)
            )
        }

        binding.deviceList.adapter = adapter

        deviceViewModel.devices.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onResume() {
        val workplaceId = requireActivity().intent.getIntExtra(getString(R.string.workplaceId), 0)

        deviceViewModel.getDevices(workplaceId)
        super.onResume()
    }

}
