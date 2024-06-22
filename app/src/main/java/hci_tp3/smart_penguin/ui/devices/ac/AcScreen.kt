package hci_tp3.smart_penguin.ui.devices.ac


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.ui.getViewModelFactory

@Composable
fun AcScreen(
    acViewModel: AcViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiAcUiState by acViewModel.uiState.collectAsState()
    var checked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if(checked){
            Text(
                text = stringResource(id = R.string.execute_turn_off)
            ) } else {
            Text(
                text = stringResource(id = R.string.execute_turn_on)
            ) }
        Switch(
            checked = uiAcUiState.currentDevice?.status == Status.ON,
            onCheckedChange = {
                when (uiAcUiState.currentDevice?.status) {
                    Status.ON -> acViewModel.off()
                    Status.OFF -> acViewModel.on()
                    else -> {}
                }
                checked = !checked
            }
        )
        Text(
            text = stringResource(id = R.string.ac_temp_set)
        )
        var sliderPosition by remember { mutableFloatStateOf(24f) }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it; acViewModel.setTemperature(sliderPosition.toInt()) },
            steps = 21,
            valueRange = 18f..38f
        )
        Text(text = sliderPosition.toString()+"°C")
        Text(
            text = stringResource(id = R.string.ac_mode)
        )
        //TODO: Add Segmented Button for modes: Cool,Heat,Fan
        Text(
            text = stringResource(id = R.string.ac_h_swing)
        )
        //TODO: Add menu for H.Swing: auto,22,45,67,90
        Text(
            text = stringResource(id = R.string.ac_v_swing)
        )
        //TODO: Add menu for V.Swing: auto,-90,-45,0,45,90
        Text(
            text = stringResource(id = R.string.ac_speed)
        )
        //TODO: Add menu for Speed: auto,25,50,75,100
    }
}