package com.example.datanermobile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Insights : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.insights)


        }

        fun mostrarFrag1(v: View){
            supportFragmentManager.beginTransaction().replace(R.id.frame1, Fragment1()).commit()
        }

        fun mostrarFrag2(v: View){
            supportFragmentManager.beginTransaction().replace(R.id.frame1, Fragment2()).commit()
        }
        fun mostrarFrag3(v: View){
            supportFragmentManager.beginTransaction().replace(R.id.frame1, Fragment3()).commit()
        }
    }
