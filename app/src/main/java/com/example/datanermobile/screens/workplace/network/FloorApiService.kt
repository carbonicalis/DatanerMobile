package com.example.datanermobile.screens.workplace.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

//private const val BASE_URL = "http://54.173.83.33:7000/floor/"
private const val BASE_URL = "http://10.0.0.106:7002/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
    .build()


interface FloorApiService {

    @GET("all/workplaces/{building}")
    fun getWorkplacesAsync(@Path("building") building: Int): Deferred<List<Workplace>>

    @GET("/{building}")
    fun getFloorsAsync(@Path("building") building: Int): Deferred<List<Floor>>

    @POST(".")
    fun createFloorAsync(@Body floor: FloorRequest): Deferred<ResponseBody>

}

object FloorApi {
    val retrofitService: FloorApiService by lazy {
        retrofit.create(FloorApiService::class.java)
    }
}