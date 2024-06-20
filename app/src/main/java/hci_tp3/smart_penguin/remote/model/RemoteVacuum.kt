package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Vacuum
import hci_tp3.smart_penguin.model.VacuumMode
import hci_tp3.smart_penguin.model.VacuumStatus

class RemoteVacuum : RemoteDevice<RemoteVacuumState>() {
    override fun asModel(): Vacuum {
        return Vacuum(
            id = id,
            name = name,
            room = room?.asModel(),
            status = VacuumStatus.valueOf(state.status.uppercase()),
            mode = VacuumMode.valueOf(state.mode.uppercase()),
            batteryLevel =  state.batteryLevel
        )
    }
}