package com.example.datanermobile

import android.os.AsyncTask

class criarUserTask : AsyncTask<Usuario, Void, Usuario>(){

    override fun doInBackground(vararg params: Usuario?): Usuario {
        return ClienteJson.criar().postUsuario(params[0]!!)

    }
}