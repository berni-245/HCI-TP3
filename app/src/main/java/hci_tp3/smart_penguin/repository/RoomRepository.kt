package hci_tp3.smart_penguin.repository

import hci_tp3.smart_penguin.model.Room
import hci_tp3.smart_penguin.remote.RoomRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoomRepository(
    private val remoteDataSource: RoomRemoteDataSource
) {
    private val roomsMutex = Mutex()
    private var rooms: List<Room> = emptyList()

    private suspend fun updateCache(rooms: List<Room>) {
        roomsMutex.withLock {
            this.rooms = rooms
        }
    }

    suspend fun getRooms(refresh: Boolean = false): List<Room> {
        if (refresh || rooms.isEmpty()) {
            val result = remoteDataSource.getRooms()
            updateCache(result.map { it.asModel() })
        }

        return roomsMutex.withLock { this.rooms }
    }

    suspend fun getRoom(roomId: String): Room {
        return remoteDataSource.getRoom(roomId).asModel()
    }
}