package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Device
import hci_tp3.smart_penguin.model.Vacuum

class RemoteVacuum : RemoteDevice<RemoteVacuumState>() {
    override fun asModel(): Vacuum {
        return Vacuum(
            id = id,
            name = name,
            room = room?.asModel(),
            status = state.status,
            mode = state.mode,
            batteryLevel =  state.batteryLevel
        )
    }
}