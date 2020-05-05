package com.example.datanermobile

import android.app.DownloadManager
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.system.OsConstants
import android.view.View
import com.example.datanermobile.building.BuildingActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback


class LoginActivity : AppCompatActivity() {

    var preferencias:SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferencias = getPreferences(Context.MODE_PRIVATE)

        val user = preferencias?.getString(getString(R.string.username_key), "")
        val pass = preferencias?.getString(getString(R.string.password_key), "")
      //  val request = Intent(this, LoginRequests::class.java)
       // startActivityForResult(intent, REQUEST_CODE)
        edUser.setText(user)
        edPwd.setText(pass)



    }

    fun enviar(v:View){
        val user = edUser.text.toString()
        val pass: String = edPwd.text.toString()
        val tela2 = Intent(this, BuildingActivity::class.java)
        val editor = preferencias?.edit()



        editor?.putString(getString(R.string.username_key), user)
        editor?.putString(getString(R.string.password_key), pass)

        editor?.commit()
        startActivity(tela2)



    }




    //companion object {
      //  private const val REQUEST_CODE = 200
    //}


}


