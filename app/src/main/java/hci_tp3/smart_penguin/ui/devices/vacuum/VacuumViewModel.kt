package hci_tp3.smart_penguin.ui.devices.vacuum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hci_tp3.smart_penguin.DataSourceException
import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.model.Vacuum
import hci_tp3.smart_penguin.model.VacuumMode
import hci_tp3.smart_penguin.repository.DeviceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VacuumViewModel (
    private val repository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VacuumUiState())
    val uiState = _uiState.asStateFlow()

    fun start() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Vacuum.START_ACTION) },
        { state, _ -> state }
    )

    fun pause() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Vacuum.PAUSE_ACTION) },
        { state, _ -> state }
    )

    fun dock() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Vacuum.DOCK_ACTION) },
        { state, _ -> state }
    )

    fun setMode(mode: VacuumMode) = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Vacuum.SET_MODE_ACTION, arrayOf(mode.apiString)) },
        { state, _ -> state }
    )

    fun setLocation(roomId: String) = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Vacuum.SET_LOCATION_ACTION, arrayOf(roomId)) },
        { state, _ -> state }
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (VacuumUiState, R) -> VacuumUiState
    ): Job = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, error = null) }
        runCatching {
            block()
        }.onSuccess { response ->
            _uiState.update { updateState(it, response).copy(loading = false) }
        }.onFailure { e ->
            _uiState.update { it.copy(loading = false, error = handleError(e)) }
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }
}