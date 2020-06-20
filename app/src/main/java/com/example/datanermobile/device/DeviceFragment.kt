package com.example.datanermobile.device

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

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

        deviceViewModel.devices.observe(viewLifecycleOwner, Observer {
            it?.let {
                println(it)
            }
        })

        return binding.root
    }

}
