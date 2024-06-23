package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Vacuum
import hci_tp3.smart_penguin.model.state.VacuumMode
import hci_tp3.smart_penguin.model.state.VacuumStatus
import hci_tp3.smart_penguin.remote.model.state.RemoteVacuumState

class RemoteVacuum : RemoteDevice<RemoteVacuumState>() {
    override fun asModel(): Vacuum {
        return Vacuum(
            id = id,
            name = name,
            room = room?.asModel(),
            status = VacuumStatus.valueOf(state.status.uppercase()),
            mode = VacuumMode.valueOf(state.mode.uppercase()),
            batteryLevel =  state.batteryLevel,
            location = state.location?.asModel()
        )
    }
}