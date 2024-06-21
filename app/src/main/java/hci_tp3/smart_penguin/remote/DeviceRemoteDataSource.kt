package hci_tp3.smart_penguin.remote

import hci_tp3.smart_penguin.remote.api.DeviceService
import hci_tp3.smart_penguin.remote.model.RemoteDevice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.selects.onTimeout
import kotlinx.coroutines.selects.select

class DeviceRemoteDataSource(
    private val deviceService: DeviceService
) : RemoteDataSource() {

    private val updateChannel = Channel<Unit>(Channel.CONFLATED)

    @OptIn(ExperimentalCoroutinesApi::class)
    val devices: Flow<List<RemoteDevice<*>>> = flow {
        while (true) {
            val devices = handleApiResponse {
                deviceService.getDevices()
            }
            emit(devices)
            select {
                updateChannel.onReceive {
                }
                onTimeout(DELAY) {
                }
            }
        }
    }

    suspend fun updateDevices() {
        updateChannel.send(Unit)
    }

    suspend fun getDevice(deviceId: String): RemoteDevice<*> {
        return handleApiResponse {
            deviceService.getDevice(deviceId)
        }
    }

    suspend fun executeDeviceAction(
        deviceId: String,
        action: String,
        parameters: Array<*>
    ): Array<*> {
        return handleApiResponse {
            deviceService.executeDeviceAction(deviceId, action, parameters)
        }
    }

    companion object {
        const val DELAY: Long = 10000
    }
}