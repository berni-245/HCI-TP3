package hci_tp3.smart_penguin.ui.devices.blind

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.state.BlindStatus
import hci_tp3.smart_penguin.ui.getViewModelFactory
import hci_tp3.smart_penguin.ui.navigation.AppDestinations

@Composable
fun BlindScreen(
    blindViewModel: BlindViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiBlindUiState by blindViewModel.uiState.collectAsState()
    var checked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if(checked){
            Text(
                text = stringResource(id = R.string.open_blinds_action)
            ) } else {
            Text(
                text = stringResource(id = R.string.close_blind_action)
            ) }
        Switch(
            checked = uiBlindUiState.currentDevice?.status == BlindStatus.CLOSED,
            onCheckedChange = {
                when (uiBlindUiState.currentDevice?.status) {
                    BlindStatus.CLOSED -> blindViewModel.open()
                    BlindStatus.OPENED -> blindViewModel.close()
                    else -> {}
                }
                checked = !checked
            }
        )
        Text(
            text = stringResource(id = R.string.set_level_blind_action)
        )
        var sliderPosition by remember { mutableFloatStateOf(100f) }
        Slider(
            value = sliderPosition,
            onValueChange = { newValue ->
                sliderPosition = newValue
                blindViewModel.setLevel(sliderPosition.toInt())
            },
            valueRange = 1f..100f
        )
    }
    val navController = rememberNavController()
    Button(onClick = { navController.navigate(AppDestinations.DEVICES.route) }) {
        Text(stringResource(id = R.string.close_blind_action))
    }

}