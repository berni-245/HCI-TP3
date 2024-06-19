package hci_tp3.smart_penguin.ui.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hci_tp3.smart_penguin.DataSourceException
import hci_tp3.smart_penguin.model.Ac
import hci_tp3.smart_penguin.model.Blind
import hci_tp3.smart_penguin.repository.DeviceRepository
import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.model.Lamp
import hci_tp3.smart_penguin.model.Vacuum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DevicesViewModel(
    repository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        collectOnViewModelScope(
            repository.devices,
        ) { state, response ->
            state.copy(
                lamps = response.filterIsInstance<Lamp>(),
                acs = response.filterIsInstance<Ac>(),
                blinds = response.filterIsInstance<Blind>(),
                vacuums = response.filterIsInstance<Vacuum>()
            )
        }
    }

    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (DevicesUiState, T) -> DevicesUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { it.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { updateState(it, response) } }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }
}