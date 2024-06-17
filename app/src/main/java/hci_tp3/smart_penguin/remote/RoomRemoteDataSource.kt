package hci_tp3.smart_penguin.remote

import hci_tp3.smart_penguin.remote.api.RoomService
import hci_tp3.smart_penguin.remote.model.RemoteRoom

class RoomRemoteDataSource(
    private val roomService: RoomService
) : RemoteDataSource() {

    suspend fun getRooms(): List<RemoteRoom> {
        return handleApiResponse {
            roomService.getRooms()
        }
    }

    suspend fun getRoom(roomId: String): RemoteRoom {
        return handleApiResponse {
            roomService.getRoom(roomId)
        }
    }

    suspend fun addRoom(room: RemoteRoom): RemoteRoom {
        return handleApiResponse {
            roomService.addRoom(room)
        }
    }

    suspend fun modifyRoom(room: RemoteRoom): Boolean {
        return handleApiResponse {
            roomService.modifyRoom(room.id!!, room)
        }
    }

    suspend fun deleteRoom(roomId: String): Boolean {
        return handleApiResponse {
            roomService.deleteRoom(roomId)
        }
    }
}