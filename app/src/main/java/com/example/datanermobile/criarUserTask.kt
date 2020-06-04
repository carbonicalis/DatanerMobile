package com.example.datanermobile

import android.os.AsyncTask
import java.lang.Exception

class criarUserTask : AsyncTask<Usuario, Void, Usuario>(){

    override fun doInBackground(vararg params: Usuario?): Usuario? {

        try {
            return ClienteJson.criar().postUsuario(params[0]!!)
        } catch (e:Exception) {
            e.printStackTrace()
            return null
        }

    }
}