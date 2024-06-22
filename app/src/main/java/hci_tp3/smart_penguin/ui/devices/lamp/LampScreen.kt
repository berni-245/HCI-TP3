package hci_tp3.smart_penguin.ui.devices.lamp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.ui.getViewModelFactory
import hci_tp3.smart_penguin.ui.navigation.AppDestinations

@Composable
fun LampScreen( onNavigateDestination: (String) -> Unit,
    lampViewModel: LampViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiLampUiState by lampViewModel.uiState.collectAsState()
    var checked by remember { mutableStateOf(false)}
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if(checked){
        Text(
            text =stringResource(id = R.string.execute_turn_off)
        ) } else {
        Text(
            text = stringResource(id = R.string.execute_turn_on)
        ) }
        Switch(
            checked = uiLampUiState.currentDevice?.status == Status.ON,
            onCheckedChange = {
                when (uiLampUiState.currentDevice?.status) {
                    Status.ON -> lampViewModel.turnOff()
                    Status.OFF -> lampViewModel.turnOn()
                    else -> {}
                }
                checked = !checked
            }
        )
        Text(
            text = stringResource(id = R.string.lamp_intensity)
        )
        var sliderPosition by remember { mutableFloatStateOf(50f) }
        Slider(
            value = sliderPosition,
            onValueChange = { newValue ->
                sliderPosition = newValue
                lampViewModel.setBrightness(newValue.toInt())
            },
            valueRange = 1f..100f
        )
        Text(
            text = stringResource(id = R.string.lamp_color)
        )
        ColorPickerController().setDebounceDuration(10L)
        HsvColorPicker(modifier = Modifier
            .fillMaxWidth()
            .height(450.dp), controller = ColorPickerController(), onColorChanged = {
            colorEnvelope: ColorEnvelope -> lampViewModel.setColor(colorEnvelope.hexCode)
        })
    }
    val navController = rememberNavController()
    Button(onClick = { onNavigateDestination(AppDestinations.DEVICES.route) }) {
        Text(stringResource(id = R.string.close_blind_action))
    }

}