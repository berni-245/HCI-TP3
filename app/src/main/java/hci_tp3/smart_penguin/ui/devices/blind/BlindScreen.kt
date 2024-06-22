package hci_tp3.smart_penguin.ui.devices.blind

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.state.BlindStatus
import hci_tp3.smart_penguin.ui.getViewModelFactory

@Composable
fun BlindScreen(
    blindViewModel: BlindViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiBlindUiState by blindViewModel.uiState.collectAsState()
    if (uiBlindUiState.currentDevice == null) {
        // Mostrar un indicador de carga mientras los datos se están obteniendo
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            CircularProgressIndicator()
        }
    } else {
    Column(
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Text(
            text = uiBlindUiState.currentDevice!!.name,
            fontSize = 22.sp,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        var checked by remember { mutableStateOf(uiBlindUiState.currentDevice?.status == BlindStatus.CLOSED) }
        if (checked) {
            Text(
                text = stringResource(id = R.string.open_blinds_action)
            )
        } else {
            Text(
                text = stringResource(id = R.string.close_blind_action)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Switch(
            checked = checked,
            onCheckedChange = {
                when (uiBlindUiState.currentDevice?.status) {
                    BlindStatus.CLOSED -> blindViewModel.open()
                    BlindStatus.OPENED -> blindViewModel.close()
                    else -> {}
                }
                checked = !checked
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.set_level_blind_action)
        )
        var sliderPosition by remember { mutableIntStateOf(uiBlindUiState.currentDevice!!.level) }
        Spacer(modifier = Modifier.height(6.dp))
        Slider(
            value = sliderPosition.toFloat(),
            onValueChange = {
                sliderPosition = it.toInt()
                blindViewModel.setLevel(sliderPosition)
            },
            valueRange = 1f..100f
        )
    }
    }
}