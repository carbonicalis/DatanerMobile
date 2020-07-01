package com.example.datanermobile.screens.workplace.network

import com.appdynamics.eumagent.runtime.HttpRequestTracker
import com.appdynamics.eumagent.runtime.Instrumentation
import com.example.datanermobile.appdynamics.AppDynamics
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.net.URL

private const val BASE_URL = "http://54.173.83.33/workplace/"
//private const val BASE_URL = "http://10.0.0.106:7003/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
    .build()


interface WorkplaceApiService {

    @POST(".")
    fun createWorkplaceAsync(@Body workplace: WorkplaceRequest): Deferred<ResponseBody>

    @PUT(".")
    fun updateWorkplaceAsync(@Body workplace: WorkplaceUpdate): Deferred<ResponseBody>

}

object WorkplaceApi {
    val retrofitService: WorkplaceApiService by lazy {
        retrofit.create(WorkplaceApiService::class.java)
    }

    fun sendRequestToAppDynamics(statusCode: Int) {
        val tracker: HttpRequestTracker = Instrumentation.beginHttpRequest(URL(BASE_URL))
        tracker.withResponseCode(statusCode)
            .reportDone()

//        AppDynamics().sendRequest(statusCode, BASE_URL)
    }
}