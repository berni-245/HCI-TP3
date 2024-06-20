package hci_tp3.smart_penguin.remote.model

import AcMode
import hci_tp3.smart_penguin.model.Ac
import hci_tp3.smart_penguin.model.Status

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