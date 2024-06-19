package hci_tp3.smart_penguin.remote.api

import hci_tp3.smart_penguin.remote.model.RemoteDevice
import hci_tp3.smart_penguin.remote.model.RemoteResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface DeviceService {
    @GET("devices")
    suspend fun getDevices(): Response<RemoteResult<List<RemoteDevice<*>>>>
    @GET("devices/{deviceId}")
    suspend fun getDevice(@Path("deviceId") deviceId: String): Response<RemoteResult<RemoteDevice<*>>>

    @PUT("devices/{deviceId}/{action}")
    suspend fun executeDeviceAction(
        @Path("deviceId") deviceId: String,
        @Path("action") action: String,
        @Body parameters: Array<*>
    ): Response<RemoteResult<Array<*>>>
}