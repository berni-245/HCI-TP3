package hci_tp3.smart_penguin.ui.devices.blind

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hci_tp3.smart_penguin.DataSourceException
import hci_tp3.smart_penguin.model.Blind
import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.repository.DeviceRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlindViewModel (
    private val repository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BlindUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.currentDevice
        ) { state, response -> state.copy(currentDevice = response as Blind?)}
    }

    fun open() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Blind.OPEN_ACTION) },
        { state, _ -> state }
    )

    fun close() = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Blind.CLOSE_ACTION) },
        { state, _ -> state }
    )

    fun setLevel(level: Int) = runOnViewModelScope(
        { repository.executeDeviceAction(uiState.value.currentDevice?.id!!, Blind.SET_LEVEL_ACTION, arrayOf(level)) },
        { state, _ -> state }
    )

    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (BlindUiState, T) -> BlindUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { it.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { updateState(it, response) } }
    }
    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (BlindUiState, R) -> BlindUiState
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