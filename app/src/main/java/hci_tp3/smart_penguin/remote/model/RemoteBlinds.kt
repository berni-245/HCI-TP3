package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Blinds
import hci_tp3.smart_penguin.model.Device

class RemoteBlinds : RemoteDevice<RemoteBlindsState>(){
    override fun asModel(): Device {
        return Blinds(
            id = id,
            name = name,
            room = room?.asModel(),
            status = state.status,
            level = state.level,
            currentLevel = state.currentLevel
        )
    }
}