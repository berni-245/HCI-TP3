package hci_tp3.smart_penguin.ui.devices.ac

import hci_tp3.smart_penguin.model.Ac
import hci_tp3.smart_penguin.model.Error

data class AcUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val currentDevice: Ac? = null
)

val AcUiState.canExecuteAction: Boolean get() = currentDevice != null && !loading