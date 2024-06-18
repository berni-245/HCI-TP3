package hci_tp3.smart_penguin.ui.devices.lamp

import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.model.Lamp

data class LampUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val currentDevice: Lamp? = null
)

val LampUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading