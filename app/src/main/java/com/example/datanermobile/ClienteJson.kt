package com.example.datanermobile

import com.appdynamics.eumagent.runtime.HttpRequestTracker
import com.appdynamics.eumagent.runtime.Instrumentation
import com.example.datanermobile.appdynamics.AppDynamics
import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import java.net.URL

object ClienteJson {

    private const val LOGIN_BASE_URL = "http://54.173.83.33/login"
//    private const val LOGIN_BASE_URL = "http://10.0.0.106:7005"

    fun criar(): UsuarioRequests {
        return Feign.builder()
            .decoder(GsonDecoder())
            .encoder(GsonEncoder())
//            .target(UsuarioRequests::class.java, LOGIN_BASE_URL)
            .target(UsuarioRequests::class.java, LOGIN_BASE_URL)
    }

    fun sendRequestToAppDynamics(statusCode: Int) {
        val tracker: HttpRequestTracker = Instrumentation.beginHttpRequest(URL(LOGIN_BASE_URL))
        tracker.withResponseCode(statusCode)
            .reportDone()
//        AppDynamics().sendRequest(statusCode, LOGIN_BASE_URL)
    }
}