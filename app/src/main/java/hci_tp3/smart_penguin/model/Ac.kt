package hci_tp3.smart_penguin.model

import hci_tp3.smart_penguin.remote.model.RemoteAc
import hci_tp3.smart_penguin.remote.model.RemoteAcState
import hci_tp3.smart_penguin.remote.model.RemoteDevice
import hci_tp3.smart_penguin.remote.model.RemoteLamp

class Ac (
    id: String?,
    name: String,
    val room: Room?,
    val status: Status,
    val temperature: Int,
    val mode: AcMode,
    val verticalSwing: String,
    val horizontalSwing: String,
    val fanSpeed: String
) : Device(id, name, DeviceType.AC){

    companion object {
        // TODO: Agregar nombres de acciones
    }
}
