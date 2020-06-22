package com.example.datanermobile

import android.app.DownloadManager
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.system.OsConstants
import android.view.View
import feign.FeignException
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity() {

    var preferencias:SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferencias = getPreferences(Context.MODE_PRIVATE)

        val user = preferencias?.getString("usuário", "")
      //  val request = Intent(this, LoginRequests::class.java)
       // startActivityForResult(intent, REQUEST_CODE)
        edUser.setText(user)
        
    }

    fun enviar(v:View){
        val user = edUser.text.toString()
        val pass: String = edPwd.text.toString()
       // val tela2 = Intent(this, BuildingActivity::class.java)
        val editor = preferencias?.edit()
        editor?.putString("username", user)
        editor?.putString("password", pass)
        val msgErro = "Não foi possível criar o usuário"
        editor?.commit()
        //startActivity(BuildingActivity)
               
        val res = criarUserTask().execute(Usuario(user, pass)).get()
        if (res==null){
           tvMessage.text= msgErro
        } else{
            tvMessage.text= "${res.token} - ${res.userName}"
        }


    }
    


}


