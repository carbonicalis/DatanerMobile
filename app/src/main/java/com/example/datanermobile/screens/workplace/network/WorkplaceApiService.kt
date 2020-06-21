package com.example.datanermobile.screens.workplace.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://54.173.83.33:7000/workplace/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface WorkplaceApiService {

    @POST("workplace/")
    suspend fun createWorkplaceAsync(@Body workplace: WorkplaceRequest): Deferred<ResponseBody>

    @PUT("workplace/")
    fun updateWorkplaceAsync(@Body workplace: Workplace): Deferred<ResponseBody>

}

object WorkplaceApi {
    val retrofitService: WorkplaceApiService by lazy {
        retrofit.create(WorkplaceApiService::class.java)
    }
}