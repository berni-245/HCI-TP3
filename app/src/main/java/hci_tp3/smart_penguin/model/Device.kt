package hci_tp3.smart_penguin.model

import hci_tp3.smart_penguin.remote.model.RemoteDevice

abstract class Device(
    val id: String?,
    val name: String,
    val type: DeviceType
) {
}
