package hci_tp3.smart_penguin.ui.devices.ac


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import hci_tp3.smart_penguin.model.state.AcMode
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.ui.getViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcScreen(
    acViewModel: AcViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiAcUiState by acViewModel.uiState.collectAsState()
    if (uiAcUiState.currentDevice == null) {
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
            text = uiAcUiState.currentDevice!!.name,
            fontSize = 22.sp,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        var checked by remember { mutableStateOf(uiAcUiState.currentDevice?.status == Status.ON) }
        if(checked){
            Text(
                text = stringResource(id = R.string.execute_turn_off)
            ) } else {
            Text(
                text = stringResource(id = R.string.execute_turn_on)
            ) }
        Spacer(modifier = Modifier.height(6.dp))
        Switch(
            checked = checked,
            onCheckedChange = {
                when (uiAcUiState.currentDevice?.status) {
                    Status.ON -> acViewModel.off()
                    Status.OFF -> acViewModel.on()
                    else -> {}
                }
                checked = !checked
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.ac_temp_set)
        )
        Spacer(modifier = Modifier.height(6.dp))
        var sliderPosition by remember { mutableFloatStateOf(uiAcUiState.currentDevice?.temperature?.toFloat()?:0f) }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it; acViewModel.setTemperature(sliderPosition.toInt()) },
            steps = 21,
            valueRange = 18f..38f
        )
        Text(text = "$sliderPosition°C")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.ac_mode)
        )
        Spacer(modifier = Modifier.height(6.dp))
        var selectedIndex by remember { mutableIntStateOf(uiAcUiState.currentDevice?.mode?.ordinal?:0) }
        val options = listOf(stringResource(id = R.string.ac_mode_cool), stringResource(id = R.string.ac_mode_heat), stringResource(id = R.string.ac_mode_fan))
        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { selectedIndex = index; acViewModel.setMode(AcMode.entries[index]) },
                    selected = index == selectedIndex
                ) {
                    Text(label)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.ac_h_swing)
        )
        Spacer(modifier = Modifier.height(6.dp))
        var expanded by remember { mutableStateOf(false) }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("Auto") }, onClick = { acViewModel.setHorizontalSwing("auto")})
            DropdownMenuItem(text = { Text("22º") }, onClick = { acViewModel.setHorizontalSwing("22")})
            DropdownMenuItem(text = { Text("45º") }, onClick = { acViewModel.setHorizontalSwing("45")})
            DropdownMenuItem(text = { Text("67º") }, onClick = { acViewModel.setHorizontalSwing("67")})
            DropdownMenuItem(text = { Text("90º") }, onClick = { acViewModel.setHorizontalSwing("90")})
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.ac_v_swing)
        )
        Spacer(modifier = Modifier.height(6.dp))
        var expanded2 by remember { mutableStateOf(false) }
        DropdownMenu(expanded = expanded2, onDismissRequest = { expanded2 = false }) {
            DropdownMenuItem(text = { Text("Auto") }, onClick = { acViewModel.setVerticalSwing("auto")})
            DropdownMenuItem(text = { Text("-90º") }, onClick = { acViewModel.setVerticalSwing("-90")})
            DropdownMenuItem(text = { Text("-45º") }, onClick = { acViewModel.setVerticalSwing("-45")})
            DropdownMenuItem(text = { Text("0º") }, onClick = { acViewModel.setVerticalSwing("0")})
            DropdownMenuItem(text = { Text("45º") }, onClick = { acViewModel.setVerticalSwing("45")})
            DropdownMenuItem(text = { Text("90º") }, onClick = { acViewModel.setVerticalSwing("90")})
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.ac_speed)
        )
        Spacer(modifier = Modifier.height(6.dp))
        var expanded3 by remember { mutableStateOf(false) }
        DropdownMenu(expanded = expanded3, onDismissRequest = { expanded3 = false }) {
            DropdownMenuItem(text = { Text("Auto") }, onClick = { acViewModel.setFanSpeed("auto")})
            DropdownMenuItem(text = { Text("25%") }, onClick = { acViewModel.setFanSpeed("25")})
            DropdownMenuItem(text = { Text("50%") }, onClick = { acViewModel.setFanSpeed("50")})
            DropdownMenuItem(text = { Text("75%") }, onClick = { acViewModel.setFanSpeed("75")})
            DropdownMenuItem(text = { Text("100%") }, onClick = { acViewModel.setFanSpeed("100")})
        }
    }
    }
}