package com.example.datanermobile.device.network

import android.os.Parcelable
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

@Parcelize
data class AllWorkplaceDevices(
    val deviceId: String,
    val deviceDescription: String,
    val deviceState: Boolean,
    val deviceType: String,
    val workplaceId: Int,
    val tags: @RawValue List<Tag>
): Parcelable

data class Tag (
    val tagId: Int,
    val tagDescription: String,
    val buildingId: Int
)

private const val DEVICE_BASE_URL = "http://10.0.0.106:7001/"

private val retrofit = Retrofit.Builder()
    .baseUrl(DEVICE_BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
    .build()

interface DeviceApiService {
    @GET(value = "all/workplace/{id}")
    fun getDeviceAsync(@Path("id") workplaceId: Int): Deferred<List<AllWorkplaceDevices>>
}

object DeviceApi {
    val retrofitService: DeviceApiService by lazy {
        retrofit.create(DeviceApiService::class.java)
    }
}
