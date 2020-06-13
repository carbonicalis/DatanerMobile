package com.example.datanermobile.building.update

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
import com.example.datanermobile.databinding.BuildingUpdateFragmentBinding

class BuildingUpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: BuildingUpdateFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.building_update_fragment,
            container,
            false
        )

        val arguments = BuildingUpdateFragmentArgs.fromBundle(requireArguments())

        val buildingUpdateViewModelFactory =
            BuildingUpdateViewModelFactory(arguments.buildingKey, arguments.selectedProperty)

        val buildingUpdateViewModel = ViewModelProvider(
            this,
            buildingUpdateViewModelFactory
        ).get(BuildingUpdateViewModel::class.java)

        binding.buildingUpdateViewModel = buildingUpdateViewModel
        binding.lifecycleOwner = this

        binding.btBuildingUpdate.setOnClickListener {
            buildingUpdateViewModel.onUpdate(
                Building(
                    buildingId = arguments.buildingKey,
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

        binding.ivDelete.setOnClickListener {
            buildingUpdateViewModel.onDelete(arguments.buildingKey)
        }

        buildingUpdateViewModel.navigateToBuildings.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    BuildingUpdateFragmentDirections.actionBuildingUpdateFragmentToBuildingFragment()
                )
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                buildingUpdateViewModel.doneNavigating()
            }

            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        })

        println("SELECTED PROPERTY ${arguments.selectedProperty}")

        return binding.root
    }

}
