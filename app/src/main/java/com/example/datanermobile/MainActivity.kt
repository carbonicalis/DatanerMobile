package com.example.datanermobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datanermobile.building.BuildingActivity
import com.example.datanermobile.device.DeviceActivity
import com.example.datanermobile.screens.workplace.WorkplaceActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test.setOnClickListener {
            startActivity(Intent(this, BuildingActivity::class.java))
        }

        test_device.setOnClickListener {
            startActivity(Intent(this, DeviceActivity::class.java))
        }

        test_workplace.setOnClickListener {
            startActivity(Intent(this, WorkplaceActivity::class.java))
        }
    }
}
