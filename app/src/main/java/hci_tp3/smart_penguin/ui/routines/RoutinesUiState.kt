package hci_tp3.smart_penguin.ui.routines

import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.model.Routine

data class RoutinesUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val routines: List<Routine> = emptyList(),
//    val currentRoutine: Routine? = null
)

//val RoutinesUiState.canGetCurrent: Boolean get() = currentRoutine != null
//val RoutinesUiState.canExecuteCurrent: Boolean get() = canGetCurrent