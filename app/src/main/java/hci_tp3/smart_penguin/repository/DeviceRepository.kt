package hci_tp3.smart_penguin.repository

import hci_tp3.smart_penguin.model.Device
import hci_tp3.smart_penguin.remote.DeviceRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class DeviceRepository(
    private val remoteDataSource: DeviceRemoteDataSource
) {
    var devices: Flow<List<Device>> =
        remoteDataSource.devices
            .map { it.map { jt -> jt.asModel() } }
//    var currentDevice: Flow<Device>? = null
    private val _currentDevice = MutableStateFlow<Device?>(null)
    val currentDevice: StateFlow<Device?> = _currentDevice

    suspend fun getDevices() {
        remoteDataSource.updateDevices()
    }

    fun setCurrentDevice(device: Device) {
//        currentDevice =  device
        _currentDevice.value = device
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