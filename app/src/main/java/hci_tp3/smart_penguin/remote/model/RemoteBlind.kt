package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Blind
import hci_tp3.smart_penguin.model.state.BlindStatus
import hci_tp3.smart_penguin.model.Device
import hci_tp3.smart_penguin.remote.model.state.RemoteBlindState

class RemoteBlind : RemoteDevice<RemoteBlindState>(){
    override fun asModel(): Device {
        return Blind(
            id = id,
            name = name,
            room = room?.asModel(),
            status = BlindStatus.valueOf(state.status.uppercase()),
            level = state.level,
            currentLevel = state.currentLevel
        )
    }
}
