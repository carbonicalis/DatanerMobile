package com.example.datanermobile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.datanermobile.building.BuildingActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var preferencias: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferencias = getPreferences(Context.MODE_PRIVATE)

        val user = preferencias?.getString("usuário", "")
        edUser.setText(user)
    }

    @SuppressLint("SetTextI18n")
    fun enviar(v: View) {
        val user = edUser.text.toString()
        val pass: String = edPwd.text.toString()
        val editor = preferencias?.edit()
        editor?.putString("username", user)
        editor?.putString("password", pass)
        val msgErro = "Não foi possível criar o usuário"
        editor?.apply()

        val res = criarUserTask().execute(Login(user, pass)).get()
        if (res == null) {
            tvMessage.text = msgErro
        } else {
            startActivity(Intent(this, BuildingActivity::class.java))
            tvMessage.text = "${res.token} - ${res.userName}"
        }
    }
}
