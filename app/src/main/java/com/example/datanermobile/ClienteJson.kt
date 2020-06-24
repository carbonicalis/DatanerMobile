package com.example.datanermobile

import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder

object ClienteJson {

    fun criar(): UsuarioRequests {
        return Feign.builder()
            .decoder(GsonDecoder())
            .encoder(GsonEncoder())
            .target(UsuarioRequests::class.java, "http://54.173.83.33/login")
//            .target(UsuarioRequests::class.java, "http://10.0.0.106:7005")
    }
}