package hci_tp3.smart_penguin.model

abstract class Device(
    val id: String?,
    val name: String,
    val type: DeviceType
) {
}