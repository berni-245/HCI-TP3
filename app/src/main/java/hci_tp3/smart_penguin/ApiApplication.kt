package hci_tp3.smart_penguin

import android.app.Application
import hci_tp3.smart_penguin.remote.DeviceRemoteDataSource
import hci_tp3.smart_penguin.remote.RoomRemoteDataSource
import hci_tp3.smart_penguin.remote.api.RetrofitClient
import hci_tp3.smart_penguin.repository.DeviceRepository
import hci_tp3.smart_penguin.repository.RoomRepository

class ApiApplication  : Application() {

    private val roomRemoteDataSource: RoomRemoteDataSource
        get() = RoomRemoteDataSource(RetrofitClient.roomService)

    private val deviceRemoteDataSource: DeviceRemoteDataSource
        get() = DeviceRemoteDataSource(RetrofitClient.deviceService)

    val roomRepository: RoomRepository
        get() = RoomRepository(roomRemoteDataSource)

    val deviceRepository: DeviceRepository
        get() = DeviceRepository(deviceRemoteDataSource)
}