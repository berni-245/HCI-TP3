package hci_tp3.smart_penguin.ui.routines

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.ui.getViewModelFactory

@Composable
fun RoutinesScreen(
    viewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory()),
) {
    val uiState by viewModel.uiState.collectAsState()

    Text("Hello Routines")
}