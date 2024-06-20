package hci_tp3.smart_penguin.ui.devices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.ui.component.ActionButton
import hci_tp3.smart_penguin.ui.devices.lamp.LampViewModel
import hci_tp3.smart_penguin.ui.devices.lamp.canExecuteAction
import hci_tp3.smart_penguin.ui.getViewModelFactory

@Composable
fun DevicesScreen(
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
    lampViewModel: LampViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsState()
    val uiLampState by lampViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        when (uiLampState.currentDevice?.status) {
            Status.ON -> {
                ActionButton(
                    text = R.string.execute_turn_off,
                    enabled = uiLampState.canExecuteAction,
                    onClick = { lampViewModel.turnOff() }
                )
            }

            else -> {
                ActionButton(
                    text = R.string.execute_turn_on,
                    enabled = uiLampState.canExecuteAction,
                    onClick = { lampViewModel.turnOn() }
                )
            }
        }
//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Text(
//                text = uiState.error?.message ?: "",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
//                fontSize = 18.sp
//            )
//            val currentDeviceData = uiLampState.currentDevice?.let {
//                "(${it.id}) ${it.name}"
//            } ?: stringResource(R.string.unknown)
//            Text(
//                text = stringResource(R.string.current_device, currentDeviceData),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
//                fontSize = 18.sp
//            )
//            val currentDeviceStatus = uiLampState.currentDevice?.let {
//                when (it.status) {
//                    Status.ON -> stringResource(R.string.status_on)
//                    Status.OFF -> stringResource(R.string.status_off)
//                }
//            } ?: stringResource(R.string.unknown)
//            Text(
//                text = stringResource(
//                    R.string.device_status,
//                    currentDeviceStatus
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
//                fontSize = 18.sp
//            )
//        }
    }
}