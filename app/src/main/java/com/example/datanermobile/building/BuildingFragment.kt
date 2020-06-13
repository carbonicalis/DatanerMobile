package com.example.datanermobile.building

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.datanermobile.R
import com.example.datanermobile.databinding.BuildingFragmentBinding

class BuildingFragment : Fragment() {

    lateinit var buildingViewModel: BuildingViewModel

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

        val buildingViewModelFactory = BuildingViewModelFactory(application)

        buildingViewModel = ViewModelProvider(
            this,
            buildingViewModelFactory
        ).get(BuildingViewModel::class.java)

        binding.buildingViewModel = buildingViewModel
        binding.lifecycleOwner = this

//        val adapter = BuildingAdapter(SleepNightListener { nightId ->
//            buildingViewModel.onBuildingUpdateClicked(nightId)
//        })

        val adapter = BuildingAdapter(BuildingListener { building, retro ->
            Toast.makeText(application, "cliquei no $${building.buildingId}", Toast.LENGTH_LONG).show()
            findNavController().navigate(
                BuildingFragmentDirections
                    .actionBuildingFragmentToBuildingUpdateFragment(building, retro)
            )
        })

        binding.btNewBuilding.setOnClickListener {
            findNavController().navigate(
                BuildingFragmentDirections
                    .actionBuildingFragmentToBuildingCreateFragment()
            )
        }

        binding.buildingList.adapter = adapter

//        buildingViewModel.buildings.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                adapter.submitList(it)
//            }
//        })

        buildingViewModel.buildingsRetrofit.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onResume() {
        println("VOLTEI NO RESUME")
        buildingViewModel.getBuildings(1)
        super.onResume()
    }
}
