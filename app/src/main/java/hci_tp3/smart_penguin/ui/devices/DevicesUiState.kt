package hci_tp3.smart_penguin.ui.devices

import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.model.Device

data class DevicesUiState(
    val isFetching: Boolean = false,
    val error: Error? = null,
    val devices: List<Device> = emptyList()
)