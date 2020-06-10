package com.example.datanermobile.screens.workplace.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL ="" // TODO (01) Atualizar o endereço

private val moshi = Moshi.Builder()
 .add(KotlinJsonAdapterFactory())
 .build()

private val retrofit = Retrofit.Builder()
 .addConverterFactory(MoshiConverterFactory.create(moshi))
 .baseUrl(BASE_URL)
 .build()


interface WorkplaceApiService {

 @GET("all/{id}")
 fun getBuildingAsync(@Path("id") id: Int): Deferred<List<Workplace>>


}
object WorkplaceApi {
 val retrofitService: WorkplaceApiService by lazy {
  retrofit.create(WorkplaceApiService::class.java)
 }
}