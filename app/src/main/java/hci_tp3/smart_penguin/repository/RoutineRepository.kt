package hci_tp3.smart_penguin.repository

import hci_tp3.smart_penguin.model.Routine
import hci_tp3.smart_penguin.remote.RoutineRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val remoteDataSource: RoutineRemoteDataSource
) {
    private var routines: List<Routine> = emptyList()
    private val routinesMutex: Mutex = Mutex()

    private suspend fun updateCache(routines: List<Routine>) {
        routinesMutex.withLock {
            this.routines = routines
        }
    }

    suspend fun getRoutines(refresh: Boolean = false): List<Routine> {
        if (refresh || routines.isEmpty()) {
            val result = remoteDataSource.getRoutines()
            updateCache(result.map { it.asModel() })
        }

        return routinesMutex.withLock { this.routines }
    }

    suspend fun getRoutine(routineId: String): Routine {
        return remoteDataSource.getRoutine(routineId).asModel()
    }

    suspend fun executeRoutine(routineId: String): List<*> {
        return remoteDataSource.executeRoutine(routineId)
    }
}