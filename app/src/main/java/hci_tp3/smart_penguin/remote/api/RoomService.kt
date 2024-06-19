package hci_tp3.smart_penguin.remote.api

import hci_tp3.smart_penguin.remote.model.RemoteResult
import hci_tp3.smart_penguin.remote.model.RemoteRoom
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomService {

    @GET("rooms")
    suspend fun getRooms(): Response<RemoteResult<List<RemoteRoom>>>

    @GET("rooms/{roomId}")
    suspend fun getRoom(@Path("roomId") roomId: String): Response<RemoteResult<RemoteRoom>>
}