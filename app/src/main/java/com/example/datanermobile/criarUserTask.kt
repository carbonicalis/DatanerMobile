package com.example.datanermobile

import android.os.AsyncTask
import java.lang.Exception

class criarUserTask : AsyncTask<Login, Void, Usuario>() {

    override fun doInBackground(vararg params: Login?): Usuario? {

        return try {
            ClienteJson.criar().postUsuario(params[0]!!)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }
}