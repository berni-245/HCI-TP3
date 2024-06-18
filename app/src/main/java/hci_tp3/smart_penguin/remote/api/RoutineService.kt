package hci_tp3.smart_penguin.remote.api

import hci_tp3.smart_penguin.remote.model.RemoteResult
import hci_tp3.smart_penguin.remote.model.RemoteRoutine
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RoutineService {

    @GET("routines")
    suspend fun getRoutines(): Response<RemoteResult<List<RemoteRoutine>>>

    @GET("routines/{routineId}")
    suspend fun getRoutine(
        @Path("routineId") routineId: String
    ): Response<RemoteResult<RemoteRoutine>>

    @PUT("routines/{routineId}/execute")
    suspend fun executeRoutine (
        @Path("routineId") routineId: String,
        @Body body: Any = {}
    ): Response<RemoteResult<Boolean>>
}