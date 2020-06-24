package com.example.datanermobile.insights

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.datanermobile.R
import com.example.datanermobile.building.network.BuildingApi
import com.example.datanermobile.device.network.DeviceApi
import com.example.datanermobile.screens.workplace.network.FloorApi
import kotlinx.android.synthetic.main.insights.txt1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Insights : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.insights)

            getStateDevice(1)



        }
    private var jobInsights = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + jobInsights)

        fun getStateDevice(buildingId: Int){
            uiScope.launch{
                val propertiesInsights = DeviceApi.retrofitService.getDeviceStateAsync(buildingId)

                try {
                    val insi = propertiesInsights.await()
                    txt1.text = "${insi.devicesOn}/${insi.allDevices}"
                } catch (e: Exception){
                    throw e
                }

        }


    }


        fun mostrarFrag1(v: View){
            supportFragmentManager.beginTransaction().replace(
                R.id.frame1,
                Fragment1()
            ).commit()
        }

        fun mostrarFrag2(v: View){
            supportFragmentManager.beginTransaction().replace(
                R.id.frame1,
                Fragment2()
            ).commit()
        }
        fun mostrarFrag3(v: View){
            supportFragmentManager.beginTransaction().replace(
                R.id.frame1,
                Fragment3()
            ).commit()
        }
    }
