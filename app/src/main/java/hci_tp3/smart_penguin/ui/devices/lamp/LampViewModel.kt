package hci_tp3.smart_penguin.ui.devices.lamp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hci_tp3.smart_penguin.DataSourceException
import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.model.Lamp
import hci_tp3.smart_penguin.repository.DeviceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LampViewModel(
    private val repository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LampUiState())
    val uiState = _uiState.asStateFlow()

    fun turnOn() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Lamp.TURN_ON_ACTION) },
        { state, _ -> state }
    )

    fun turnOff() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Lamp.TURN_OFF_ACTION) },
        { state, _ -> state }
    )

    fun setBrightness(brightness: Int) = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Lamp.SET_BRIGHTNESS_ACTION, arrayOf(brightness)) },
        { state, _ -> state }
    )

    fun setColor(color: String) = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Lamp.SET_COLOR_ACTION, arrayOf(color)) },
        { state, _ -> state }
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (LampUiState, R) -> LampUiState
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