package hci_tp3.smart_penguin.remote

import hci_tp3.smart_penguin.remote.api.RoutineService
import hci_tp3.smart_penguin.remote.model.RemoteRoutine

class RoutineRemoteDataSource(
    private val routineService: RoutineService
) : RemoteDataSource() {
    suspend fun getRoutines(): List<RemoteRoutine> {
        return handleApiResponse {
            routineService.getRoutines()
        }
    }

    suspend fun getRoutine(routineId: String): RemoteRoutine {
        return handleApiResponse {
            routineService.getRoutine(routineId)
        }
    }

    suspend fun executeRoutine(routineId: String): Boolean {
        return handleApiResponse {
            routineService.executeRoutine(routineId)
        }
    }
}