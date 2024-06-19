package hci_tp3.smart_penguin.remote.model

import AcMode
import hci_tp3.smart_penguin.model.Ac

class RemoteAc : RemoteDevice<RemoteAcState>() {

    override fun asModel(): Ac {
        return Ac(
            id = id,
            name = name,
            room = room?.asModel(),
            status = RemoteStatus.asModel(state.status),
            temperature = state.temperature,
            mode = AcMode.FAN,
            verticalSwing = state.verticalSwing,
            horizontalSwing = state.horizontalSwing,
            fanSpeed = state.fanSpeed
            )
    }

}