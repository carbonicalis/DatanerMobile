package com.example.datanermobile.insights


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.datanermobile.R
import kotlinx.android.synthetic.main.fragment_fragment1.paulista


class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application

        paulista.setOnClickListener { startActivity(Intent(application, Consumos::class.java)) }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false)


    }



}

