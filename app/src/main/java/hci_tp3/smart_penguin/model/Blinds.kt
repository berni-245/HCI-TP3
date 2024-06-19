package hci_tp3.smart_penguin.model

import hci_tp3.smart_penguin.remote.model.RemoteDevice

class Blinds (
    id: String?,
    name: String,
    val room: Room?,
    val status: String,
    val level: Int,
    val currentLevel: Int
) : Device(id, name, DeviceType.BLINDS){
    override fun asRemoteModel(): RemoteDevice<*> {
        TODO("Not yet implemented")
    }

    companion object {
        // TODO: Agregar nombres de acciones
    }
}