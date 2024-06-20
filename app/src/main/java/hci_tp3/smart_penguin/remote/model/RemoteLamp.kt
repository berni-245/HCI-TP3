package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Lamp
import hci_tp3.smart_penguin.model.Status

class RemoteLamp : RemoteDevice<RemoteLampState>() {

    override fun asModel(): Lamp {
        return Lamp(
            id = id,
            name = name,
            room = room?.asModel(),
            status = Status.valueOf(state.status.uppercase()),
            color = state.color,
            brightness = state.brightness
        )
    }
}