package com.example.datanermobile.screens.workplace

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datanermobile.R
import com.example.datanermobile.databinding.FragmentWorkplaceBinding
import kotlinx.android.synthetic.main.fragment_workplace.*

class WorkplaceFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val binding: FragmentWorkplaceBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_workplace, container, false)

        Log.i("GC WorkplaceFragment","WorkplaceFragment called")
        val application = requireNotNull(this.activity).application

        val viewModelFactory = WorkplaceViewModelFactory(application)

        val workplaceViewModel = ViewModelProvider(this, viewModelFactory).get(WorkplaceViewModel::class.java)

        binding.workplaceViewModel = workplaceViewModel

        binding.lifecycleOwner = this

        val adapter = WorkplaceAdapter()
        binding.workplaceList.adapter = adapter

        return binding.root
    }

}