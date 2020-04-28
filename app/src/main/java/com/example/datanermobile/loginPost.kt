package com.example.datanermobile
import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder

object loginPost {

    fun  criar():LoginRequests{
        return Feign.builder()
            .decoder(GsonDecoder())
            .encoder(GsonEncoder())
            .target(LoginRequests::class.java, "52.4.141.220/login")
    }

}