package hci_tp3.smart_penguin.model

import hci_tp3.smart_penguin.remote.model.RemoteDevice

class Vacuum (
    id: String?,
    name: String,
    val room: Room?,
    val status: String,
    val mode: String,
    val batteryLevel: Int
) : Device(id, name, DeviceType.VACUUM){

    companion object {
        // TODO: Agregar nombres de acciones
    }
}