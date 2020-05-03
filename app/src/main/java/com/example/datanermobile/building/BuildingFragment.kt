package com.example.datanermobile.building

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
import com.example.datanermobile.building.network.BuildingDatabase
import com.example.datanermobile.databinding.BuildingFragmentBinding

class BuildingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: BuildingFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.building_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = BuildingDatabase
            .getInstance(application).buildingDatabaseDao

        val buildingViewModelFactory = BuildingViewModelFactory(dataSource, application)

        val buildingViewModel = ViewModelProvider(
            this,
            buildingViewModelFactory
        ).get(BuildingViewModel::class.java)

        binding.buildingViewModel = buildingViewModel
        binding.lifecycleOwner = this

//        val adapter = BuildingAdapter(SleepNightListener { nightId ->
//            buildingViewModel.onBuildingUpdateClicked(nightId)
//        })

        val adapter = BuildingAdapter(BuildingListener { buildingId ->
            Toast.makeText(application, "cliquei no $buildingId", Toast.LENGTH_LONG).show()
            buildingId.let {
                findNavController().navigate(BuildingFragmentDirections
                    .actionBuildingFragmentToBuildingUpdateFragment(it))
            }
        })

        binding.buildingList.adapter = adapter

        buildingViewModel.buildings.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
