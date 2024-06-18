package hci_tp3.smart_penguin.remote.api

import hci_tp3.smart_penguin.BuildConfig
import hci_tp3.smart_penguin.remote.model.RemoteDevice
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

private val httpLoggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(httpLoggingInterceptor)
    .build()

private val gson = GsonBuilder()
    .registerTypeAdapter(Date::class.java, DateTypeAdapter())
    .registerTypeAdapter(RemoteDevice::class.java, DeviceTypeAdapter())
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(okHttpClient)
    .build()

object RetrofitClient {
    val roomService: RoomService by lazy {
        retrofit.create(RoomService::class.java)
    }

    val deviceService : DeviceService by lazy {
        retrofit.create(DeviceService::class.java)
    }

    val routineService : RoutineService by lazy {
        retrofit.create(RoutineService::class.java)
    }
}