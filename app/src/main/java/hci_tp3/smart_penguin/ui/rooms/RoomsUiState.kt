package hci_tp3.smart_penguin.ui.rooms

import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.model.Room

data class RoomsUiState(
    val loading: Boolean = false,
    val error: Error? = null,
    val rooms: List<Room> = emptyList(),
    val currentRoom: Room? = null
)

val RoomsUiState.canGetCurrent: Boolean get() = currentRoom != null
val RoomsUiState.canModify: Boolean get() = currentRoom != null
val RoomsUiState.canDelete: Boolean get() = canModify
