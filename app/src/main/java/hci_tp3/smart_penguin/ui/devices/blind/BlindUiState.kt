package hci_tp3.smart_penguin.ui.devices.blind

import hci_tp3.smart_penguin.model.Blind
import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.ui.devices.lamp.LampUiState

data class BlindUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val currentDevice: Blind? = null
)

val BlindUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading