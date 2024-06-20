package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.state.AcMode
import hci_tp3.smart_penguin.model.Ac
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.remote.model.state.RemoteAcState

class RemoteAc : RemoteDevice<RemoteAcState>() {

    override fun asModel(): Ac {
        return Ac(
            id = id,
            name = name,
            room = room?.asModel(),
            status = Status.valueOf(state.status.uppercase()),
            temperature = state.temperature,
            mode = AcMode.valueOf(state.mode.uppercase()),
            verticalSwing = state.verticalSwing,
            horizontalSwing = state.horizontalSwing,
            fanSpeed = state.fanSpeed
            )
    }

}