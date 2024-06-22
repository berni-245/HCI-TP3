package hci_tp3.smart_penguin.repository

import hci_tp3.smart_penguin.model.Device
import hci_tp3.smart_penguin.remote.DeviceRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

var currentDeviceId: String = ""

class DeviceRepository(
    private val remoteDataSource: DeviceRemoteDataSource
) {
    var devices: Flow<List<Device>> =
        remoteDataSource.devices
            .map { it.map { jt -> jt.asModel() } }

    val currentDevice = devices.map { it.firstOrNull { jt -> jt.id == currentDeviceId }}

    suspend fun getDevices() {
        remoteDataSource.updateDevices()
    }

    fun setCurrentDevice(deviceId: String) {
        currentDeviceId = deviceId
    }
    suspend fun getDevice(deviceId: String): Device {
        return remoteDataSource.getDevice(deviceId).asModel()
    }

    suspend fun executeDeviceAction(
        deviceId: String,
        action: String,
        parameters: Array<*> = emptyArray<Any>()
    ): Array<*> {
        return remoteDataSource.executeDeviceAction(deviceId, action, parameters)
    }
}