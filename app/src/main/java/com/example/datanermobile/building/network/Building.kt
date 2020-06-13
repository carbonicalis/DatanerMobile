package com.example.datanermobile.building.network

import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import feign.Feign
import feign.Param
import feign.RequestLine
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
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
data class Building(
    val buildingId: Int = 0,
    val name: String = "Test Name",
    val country: String = "Test Country",
    val state: String = "Test State",
    val city: String = "Test City",
    val addressType: String = "Test Address Type",
    val address: String = "Test Address",
    val addressNumber: Int = 0,
    val zipCode: String = "Test Zip Code",
    val companyId: Int = 0
//    val deviceState: AllDeviceState? = null
) : Parcelable

data class AllDeviceState(
    val devicesOn: Int,
    val devicesOff: Int,
    val allDevices: Int
)

interface BuildingRequests {

    @RequestLine("GET /all/{id}")
    fun getBuildings(@Param("id") id: Int): LiveData<List<Building>>
}

object BuildingRequestsBuilder {
    fun getBuildings(): BuildingRequests {
        return Feign.builder()
            .decoder(GsonDecoder())
            .encoder(GsonEncoder())
            .target(BuildingRequests::class.java, "https://52.4.141.220/building")
    }
}

//class BuildingAsync {
//    private var viewModelJob = Job()
//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
//
//    private suspend fun getBuildings(id: Int): LiveData<List<Building>> {
//        return withContext(Dispatchers.IO) {
//            return@withContext BuildingRequestsBuilder.getBuildings().getBuildings(id)
//        }
//    }
//
//    fun onGetBuildings(id: Int): LiveData<List<Building>>? {
//        lateinit var buildings: LiveData<List<Building>>
//
//        uiScope.launch {
//            println(getBuildings(id))
//            buildings = getBuildings(id)
//        }
//
//        return buildings.also {
//            println(it)
//        }
//    }
//}

//class BuildingAsync : AsyncTask<String, Void, LiveData<List<Building>>>() {
//    override fun doInBackground(vararg params: String?): LiveData<List<Building>> {
//        return BuildingRequestsBuilder.getBuildings().getBuildings(params[0]!!.toInt())
//    }
//}

@Parcelize
data class BuildingRetrofit(
    val buildingId: Int,
    val name: String,
    val country: String,
    val state: String,
    val city: String,
    val addressType: String,
    val address: String,
    val addressNumber: Int,
    val zipCode: String,
    val companyId: Int,
    val deviceState: @RawValue AllDeviceState? = null
): Parcelable

data class BuildingRetrofitPut(
    val buildingId: Int,
    val name: String,
    val country: String,
    val state: String,
    val city: String,
    val addressType: String,
    val address: String,
    val addressNumber: Int,
    val zipCode: String,
    val companyId: Int
)

private const val BUILDING_BASE_URL = "http://10.0.0.106:7000/"
//private const val BUILDING_BASE_URL = "http://52.4.141.220/"
//private const val BUILDING_BASE_URL = "https://52.4.141.220/"
//private const val BUILDING_BASE_URL = "https://52.4.141.220/building/"
//private const val BUILDING_BASE_URL = "http://52.45.180.127:7002/"

//private val okHttpClient = OkHttpClient.Builder()
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .addCallAdapterFactory(CoroutineCallAdapterFactory())
////    .client(createOkHttpClient(Context, ))
//    .baseUrl(BUILDING_BASE_URL)
//    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BUILDING_BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
interface BuildingApiService {
//    @GET("building/all/{id}")
    @GET("all/{id}")
    fun getBuildingAsync(@Path("id") id: Int): Deferred<List<BuildingRetrofit>>

    @PUT("building/")
//    @PUT
//    fun updateBuilding(@Body building: BuildingRetrofitPut): Deferred<ResponseBody>
    fun updateBuildingAsync(@Body building: Building): Deferred<ResponseBody>
//    fun updateBuilding(@Body building: RequestBody): Deferred<Any>

//    @POST("building/")
    @POST(".")
    fun createBuildingAsync(@Body building: Building): Deferred<ResponseBody>

//    @DELETE("building/{id}")
    @DELETE("{id}")
    fun deleteBuildingAsync(@Path("id") id: Int): Deferred<ResponseBody>
}
object BuildingApi {
    val retrofitService: BuildingApiService by lazy {
        retrofit.create(BuildingApiService::class.java)
    }
}

//private fun createOkHttpClient(context: Context): OkHttpClient {
//    var client = OkHttpClient.Builder()
//
//    client.sslSocketFactory(SslUtils.getSslContextForCertificateFile(context, "my_certificate.pem").socketFactory)
//
//    return client.build()
//}
//
//object SslUtils {
//
//    fun getSslContextForCertificateFile(context: Context, fileName: String): SSLContext {
//        try {
//            val keyStore = SslUtils.getKeyStore(context, fileName)
//            val sslContext = SSLContext.getInstance("SSL")
//            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
//            trustManagerFactory.init(keyStore)
//            sslContext.init(null, trustManagerFactory.trustManagers, SecureRandom())
//            return sslContext
//        } catch (e: Exception) {
//            val msg = "Error during creating SslContext for certificate from assets"
//            e.printStackTrace()
//            throw RuntimeException(msg)
//        }
//    }
//
//    private fun getKeyStore(context: Context, fileName: String): KeyStore? {
//        var keyStore: KeyStore? = null
//        try {
//            val assetManager = context.assets
//            val cf = CertificateFactory.getInstance("X.509")
//            val caInput = assetManager.open(fileName)
//            val ca: Certificate
//            try {
//                ca = cf.generateCertificate(caInput)
////                Log.d("SslUtilsAndroid", "ca=" + (ca as X509Certificate).subjectDN)
//            } finally {
//                caInput.close()
//            }
//
//            val keyStoreType = KeyStore.getDefaultType()
//            keyStore = KeyStore.getInstance(keyStoreType)
//            keyStore!!.load(null, null)
//            keyStore.setCertificateEntry("ca", ca)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return keyStore
//    }
//
//    fun getTrustAllHostsSSLSocketFactory(): SSLSocketFactory? {
//        try {
//            // Create a trust manager that does not validate certificate chains
//            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
//
//                override fun getAcceptedIssuers(): Array<X509Certificate> {
//                    return arrayOf()
//                }
//
//                @Throws(CertificateException::class)
//                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//                }
//
//                @Throws(CertificateException::class)
//                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
//                }
//            })
//
//            // Install the all-trusting trust manager
//            val sslContext = SSLContext.getInstance("SSL")
//            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
//            // Create an ssl socket factory with our all-trusting manager
//
//            return sslContext.socketFactory
//        } catch (e: KeyManagementException) {
//            e.printStackTrace()
//            return null
//        } catch (e: NoSuchAlgorithmException) {
//            e.printStackTrace()
//            return null
//        }
//
//    }
//}
