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
    val mode: String,
    val verticalSwing: String,
    val horizontalSwing: String,
    val fanSpeed: String
) : Device(id, name, DeviceType.AC){

    override fun asRemoteModel(): RemoteDevice<RemoteAcState> {
        val state = RemoteAcState()
        state.status = Status.asRemoteModel(status)
        state.temperature = temperature
        state.mode = mode
        state.verticalSwing = verticalSwing
        state.horizontalSwing = horizontalSwing
        state.fanSpeed = fanSpeed

        val model = RemoteAc()
        model.id = id
        model.name = name
        model.room = room?.asRemoteModel()
        model.setState(state)
        return model
    }

    companion object {
        // TODO: Agregar nombres de acciones
    }
}