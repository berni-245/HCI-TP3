package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Blind
import hci_tp3.smart_penguin.model.Device

class RemoteBlind : RemoteDevice<RemoteBlindState>(){
    override fun asModel(): Device {
        return Blind(
            id = id,
            name = name,
            room = room?.asModel(),
            status = state.status,
            level = state.level,
            currentLevel = state.currentLevel
        )
    }
}
