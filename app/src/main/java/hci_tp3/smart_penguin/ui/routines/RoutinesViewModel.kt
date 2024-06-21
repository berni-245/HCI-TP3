package hci_tp3.smart_penguin.ui.routines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hci_tp3.smart_penguin.DataSourceException
import hci_tp3.smart_penguin.model.Error
import hci_tp3.smart_penguin.repository.RoutineRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoutinesViewModel(
    private val repository: RoutineRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RoutinesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getRoutines(true)
    }

    fun getRoutines(refresh: Boolean = true) = runOnViewModelScope(
        { repository.getRoutines(refresh) },
        { state, response -> state.copy(routines = response) }
    )

    fun getRoutine(routineId: String) = runOnViewModelScope(
            { repository.getRoutine(routineId) },
            { state, response -> state.copy(currentRoutine = response) }
        )


    fun executeRoutine(routineId: String = uiState.value.currentRoutine?.id!!) = runOnViewModelScope(
            { repository.executeRoutine(routineId) },
            { state, _ -> state }
        )


    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (RoutinesUiState, R) -> RoutinesUiState
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