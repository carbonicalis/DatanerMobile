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

class DeviceFragment : Fragment() {

    private lateinit var deviceViewModel: DeviceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DeviceFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.device_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val deviceFactory = DeviceViewModelFactory(application)

        deviceViewModel = ViewModelProvider(
            this,
            deviceFactory
        ).get(DeviceViewModel::class.java)

        binding.deviceViewModel = deviceViewModel
        binding.lifecycleOwner = this

        val adapter = DeviceAdapter(DeviceListener { devices ->
            Toast.makeText(application, "cliquei no ${devices.deviceId}", Toast.LENGTH_LONG).show()
        })

        binding.btNewDevice.setOnClickListener {
            findNavController().navigate(
                DeviceFragmentDirections
                    .actionDeviceFragmentToDeviceCreateFragment()
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

}
