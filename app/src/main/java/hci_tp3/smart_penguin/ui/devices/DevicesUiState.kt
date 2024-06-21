package hci_tp3.smart_penguin.ui.devices

import hci_tp3.smart_penguin.model.Ac
import hci_tp3.smart_penguin.model.Blind
import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.model.Lamp
import hci_tp3.smart_penguin.model.Vacuum

data class DevicesUiState(
    val isFetching: Boolean = false,
    val updating: Boolean = false,
    val error: Error? = null,
    val lamps: List<Lamp> = emptyList(),
    val blinds: List<Blind> = emptyList(),
    val vacuums: List<Vacuum> = emptyList(),
    val acs: List<Ac> = emptyList()
)
val DevicesUiState.hasDevices: Boolean get() = lamps.isNotEmpty() || blinds.isNotEmpty() || vacuums.isNotEmpty() || acs.isNotEmpty()