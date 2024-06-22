package hci_tp3.smart_penguin

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import hci_tp3.smart_penguin.remote.DeviceRemoteDataSource
import hci_tp3.smart_penguin.remote.RoomRemoteDataSource
import hci_tp3.smart_penguin.remote.RoutineRemoteDataSource
import hci_tp3.smart_penguin.remote.api.RetrofitClient
import hci_tp3.smart_penguin.repository.DeviceRepository
import hci_tp3.smart_penguin.repository.RoomRepository
import hci_tp3.smart_penguin.repository.RoutineRepository

class ApiApplication  : Application() {
    private val VACUUM_CHANNEL_ID = "vacuumChannel"
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.vacuum_notifications_channel)
            val descriptionText = getString(R.string.vacuum_notification_channel_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(VACUUM_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private val roomRemoteDataSource: RoomRemoteDataSource
        get() = RoomRemoteDataSource(RetrofitClient.roomService)

    private val deviceRemoteDataSource: DeviceRemoteDataSource
        get() = DeviceRemoteDataSource(RetrofitClient.deviceService)

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.routineService)

    val roomRepository: RoomRepository
        get() = RoomRepository(roomRemoteDataSource)

    val deviceRepository: DeviceRepository
        get() = DeviceRepository(deviceRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)
}