package com.example.datanermobile

import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder

object ClienteJson {

    fun criar() :UsuarioRequests {
        return Feign.builder()
            .decoder(GsonDecoder())
            .encoder(GsonEncoder())
            .target(UsuarioRequests::class.java, "https://jsonplaceholder.typicode.com")
    }
}