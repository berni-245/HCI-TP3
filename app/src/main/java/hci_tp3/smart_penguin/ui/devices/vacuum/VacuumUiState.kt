package hci_tp3.smart_penguin.ui.devices.vacuum

import androidx.annotation.Nullable
import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.model.Room
import hci_tp3.smart_penguin.model.Vacuum
import hci_tp3.smart_penguin.model.state.VacuumStatus
import hci_tp3.smart_penguin.ui.devices.blind.BlindUiState

data class VacuumUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val currentDevice: Vacuum? = null,
    val rooms: List<Room> = emptyList()
)

val VacuumUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading
val VacuumUiState.isThereBatteryLeft: Boolean get() = currentDevice?.batteryLevel!! >= 5

val VacuumUiState.isThereARoom: Boolean get() = currentDevice?.room != null
