package com.example.datanermobile

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    var preferencias:SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferencias = getPreferences(Context.MODE_PRIVATE)

        val user = preferencias?.getString("usuário", "")

        edUser.setText(user)

    }

    fun enviar(v:View){
        val user = edUser.text.toString()

        val editor = preferencias?.edit()

        editor?.putString("usuário", user)

        editor?.commit()
    }


}


