package com.example.datanermobile.building.create

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
import com.example.datanermobile.building.network.Building
import com.example.datanermobile.databinding.BuildingCreateFragmentBinding

class BuildingCreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: BuildingCreateFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.building_create_fragment,
            container,
            false
        )

        val buildingUpdateViewModelFactory =
            BuildingCreateViewModelFactory()

        val buildingCreateViewModel = ViewModelProvider(
            this,
            buildingUpdateViewModelFactory
        ).get(BuildingCreateViewModel::class.java)

        binding.buildingCreateViewModel = buildingCreateViewModel
        binding.lifecycleOwner = this

        binding.btBuildingCreate.setOnClickListener {
            buildingCreateViewModel.onInsert(
                Building(
                    name = binding.etBuildingName.text.toString(),
                    country = binding.etBuildingCountry.text.toString(),
                    state = binding.etBuildingState.text.toString(),
                    city = binding.etBuildingCity.text.toString(),
                    addressType = binding.etBuildingAddressType.text.toString(),
                    address = binding.etBuildingAddress.text.toString(),
                    addressNumber = binding.etBuildingAddressNumber.text.toString().toInt(),
                    zipCode = binding.etBuildingZipCode.text.toString(),
                    companyId = binding.etBuildingCompany.text.toString().toInt()
                )
            )
        }

        buildingCreateViewModel.navigateToBuildings.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    BuildingCreateFragmentDirections.actionBuildingCreateFragmentToBuildingFragment()
                )

                buildingCreateViewModel.doneNavigating()
            }

            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        })

        return binding.root
    }

}
