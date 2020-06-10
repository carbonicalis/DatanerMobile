package com.example.datanermobile.screens.workplace

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.datanermobile.R
import com.example.datanermobile.databinding.FragmentWorkplaceBinding
import com.google.android.material.snackbar.Snackbar

class WorkplaceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val binding: FragmentWorkplaceBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_workplace, container, false
        )

        val application = requireNotNull(this.activity).application

        val viewModelFactory = WorkplaceViewModelFactory(application)

        val workplaceViewModel =
            ViewModelProvider(this, viewModelFactory).get(WorkplaceViewModel::class.java)

        binding.workplaceViewModel = workplaceViewModel

        binding.lifecycleOwner = this

        workplaceViewModel.showNewFloorDialog.observe(this, Observer {
            if (it == true) {
                val builder = AlertDialog.Builder(this.context)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.alert_dialog_new_floor, null)
                val floorNumber = dialogLayout.findViewById<EditText>(R.id.newFloor)

                with(builder) {
                    setTitle(R.string.dialog_new_floor_title)
                    setView(dialogLayout)
                    setPositiveButton(R.string.add_floor) { dialogInterface, which ->
                        workplaceViewModel.newFloor(1, floorNumber.text.toString())
                    }
                    setNegativeButton(getString(R.string.cancelar)) { dialogInterface, which ->
                    }
                }
                val alertDialog = builder.create()
                alertDialog.show()

                val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                with(button) {
                    setPadding(0, 0, 20, 0)
                    setBackground(ContextCompat.getDrawable(this.context, R.drawable.custom_button))
                    setTextColor(Color.BLACK)

                }
            }

        })

        workplaceViewModel.showSnackBarEvent.observe(this, Observer {
            if (it == true) { // Observed state is true.
                val existingFloorMessage = Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.existing_floor_alert),
                    Snackbar.LENGTH_LONG // How long to display the message.
                )
                existingFloorMessage.view.setBackgroundColor(Color.RED)
                existingFloorMessage.show()
                workplaceViewModel.doneShowingSnackbar()
            }
        })

        workplaceViewModel.showNewWorkplaceDialog.observe(this, Observer {
            if (it == true) {

                val builder = AlertDialog.Builder(this.context)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.alert_dialog_new_workplace, null)
                val workplaceName =
                    dialogLayout.findViewById<EditText>(R.id.new_workplace_name_edit)
                val workplaceFloorNumber =
                    dialogLayout.findViewById<EditText>(R.id.new_workplace_number_edit)

                with(builder) {
                    setTitle(getString(R.string.dialog_new_workplace_title))
                    setView(dialogLayout)
                    setPositiveButton(R.string.add_workplace) { dialogInterface, i ->
                        workplaceViewModel.newWorkplace(
                            1,
                            workplaceFloorNumber.text.toString().toInt(),
                            workplaceName.text.toString()
                        )
                    }
                    setNegativeButton(getString(R.string.cancelar)) { dialogInterface, which ->
                    }
                }
                val alertDialog = builder.create()
                alertDialog.show()

                val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                with(button) {
                    setPadding(0, 0, 20, 0)
                    setBackground(ContextCompat.getDrawable(this.context, R.drawable.custom_button))
                    setTextColor(Color.BLACK)

                }
            }

        })


        val adapter =
            WorkplaceAdapter(WorkplaceListener { workplaceId, floorNumber, workplaceDescription ->
                val builder = AlertDialog.Builder(this.context)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.alert_dialog_edit_workplace, null)
                val workplaceName =
                    dialogLayout.findViewById<EditText>(R.id.update_workplace_name_edit)
                workplaceName.setText(workplaceDescription)
                val workplaceFloorNumber =
                    dialogLayout.findViewById<EditText>(R.id.update_workplace_number_edit)
                workplaceFloorNumber.setText(floorNumber.toString())

                with(builder) {
                    setTitle(getString(R.string.dialog_update_workplace_title))
                    setView(dialogLayout)
                    setPositiveButton(R.string.confirm_update_workplace) { dialogInterface, i ->

                        workplaceViewModel.updateWorkplace(
                            1,
                            workplaceId,
                            workplaceName.text.toString(),
                            workplaceFloorNumber.text.toString().toInt()
                        )
                    }
                    setNegativeButton(getString(R.string.cancelar)) { dialogInterface, which ->
                    }
                }
                val alertDialog = builder.create()
                alertDialog.show()

                val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                with(button) {
                    setPadding(0, 0, 20, 0)
                    setBackground(ContextCompat.getDrawable(this.context, R.drawable.custom_button))
                    setTextColor(Color.BLACK)

                }

            })
        binding.workplaceList.adapter = adapter


        workplaceViewModel.workplaces.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        workplaceViewModel.getWorkplaces()

        return binding.root
    }
}