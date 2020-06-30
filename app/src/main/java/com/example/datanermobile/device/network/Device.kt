package com.example.datanermobile.device.network

import android.os.Parcelable
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

data class DeviceCreate(
    val deviceId: String,
    val deviceDescription: String,
    val deviceType: String,
    val tagId: List<Int>? = null,
    val workplaceId: Int
)

data class DeviceUpdate(
    val deviceId: String,
    val deviceIdUpdate: String = deviceId,
    val deviceDescription: String,
    val deviceState: Boolean,
    val deviceType: String,
    val workplaceId: Int
)

data class DeviceStateUpdateRequestDto(
    val deviceId: String,
    val deviceState: Boolean
)

data class TotalDevice(
    val devicesOn: Int,
    val devicesOff: Int,
    val allDevices: Int
)

private const val DEVICE_BASE_URL = "http://10.0.0.106:7001/"

private val retrofit = Retrofit.Builder()
    .baseUrl(DEVICE_BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
    .build()

interface DeviceApiService {
//    @GET("device/all/workplace/{id}")
    @GET(value = "all/workplace/{id}")
    fun getDeviceAsync(@Path("id") workplaceId: Int): Deferred<List<AllWorkplaceDevices>>

    @GET(value = "/state/company/{id}" )
    fun getDeviceStateAsync(@Path("id") companyId: Int): Deferred<TotalDevice>

//    @POST("device/")
    @POST(".")
    fun createDeviceAsync(@Body device: DeviceCreate): Deferred<ResponseBody>

//    @PUT("device/")
    @PUT(".")
    fun updateDeviceAsync(@Body device: DeviceUpdate): Deferred<ResponseBody>

//    @DELETE("device/{id}")
    @DELETE("{id}")
    fun deleteDeviceAsync(@Path("id") deviceId: String): Deferred<ResponseBody>

    //    @PUT("device/state")
    @PUT("state/.")
    fun updateDeviceStateAsync(@Body deviceState: DeviceStateUpdateRequestDto): Deferred<ResponseBody>
}

object DeviceApi {
    val retrofitService: DeviceApiService by lazy {
        retrofit.create(DeviceApiService::class.java)
    }
}
