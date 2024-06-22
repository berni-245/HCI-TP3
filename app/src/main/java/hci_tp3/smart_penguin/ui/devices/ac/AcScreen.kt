package hci_tp3.smart_penguin.ui.devices.ac

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import hci_tp3.smart_penguin.model.state.AcMode
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.ui.getViewModelFactory
import kotlin.math.roundToInt

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
    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) { item{
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
        var sliderPosition by remember { mutableIntStateOf(uiAcUiState.currentDevice?.temperature?:0) }
        Slider(
            value = sliderPosition.toFloat(),
            onValueChange = { sliderPosition = it.roundToInt(); acViewModel.setTemperature(sliderPosition) },
            steps = 19,
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
        // List of options for the dropdown
        val options1 = listOf("auto", "22º", "45º", "67º", "90º")
        // State to track the expanded state of the dropdown
        var expanded1 by remember { mutableStateOf(false) }
        // State to track the currently selected option
        var selectedOption1 by remember { mutableStateOf(acViewModel.uiState.value.currentDevice?.horizontalSwing?: options1[0]) }

        ExposedDropdownMenuBox(
            expanded = expanded1,
            onExpandedChange = { expanded1 = it } // Toggle expanded state when clicked
        ) {
            // The text field that shows the currently selected option
            TextField(
                value = selectedOption1,
                onValueChange = {}, // Read-only, so no value change action needed
                readOnly = true, // Prevents user input directly
                trailingIcon = { // Icon indicating dropdown functionality
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded1)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor() // Ensures proper anchoring of the dropdown menu
            )

            // Dropdown menu that shows the list of options
            ExposedDropdownMenu(
                expanded = expanded1,
                onDismissRequest = { expanded1 = false } // Close the dropdown when clicking outside
            ) {
                options1.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) }, // Display the option text
                        onClick = {
                            selectedOption1 = option // Update the selected option
                            expanded1 = false // Close the dropdown
                            // Call the ViewModel function, stripping "º" for non-numeric values
                            acViewModel.setHorizontalSwing(option)
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.ac_v_swing)
        )
        Spacer(modifier = Modifier.height(6.dp))
        // List of options for the dropdown
        val options2 = listOf("auto", "-90º", "-45º", "0º", "45º", "90º")
        // State to track the expanded state of the dropdown
        var expanded2 by remember { mutableStateOf(false) }
        // State to track the currently selected option
        var selectedOption2 by remember { mutableStateOf(acViewModel.uiState.value.currentDevice?.verticalSwing?: options2[0]) } // Default to "Auto"

        ExposedDropdownMenuBox(
            expanded = expanded2,
            onExpandedChange = { expanded2 = it } // Toggle expanded state when clicked
        ) {
            // The text field that shows the currently selected option
            TextField(
                value = selectedOption2,
                onValueChange = {}, // Read-only, so no value change action needed
                readOnly = true, // Prevents user input directly
                trailingIcon = { // Icon indicating dropdown functionality
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded2)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor() // Ensures proper anchoring of the dropdown menu
            )

            // Dropdown menu that shows the list of options
            ExposedDropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false } // Close the dropdown when clicking outside
            ) {
                options2.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) }, // Display the option text
                        onClick = {
                            selectedOption2 = option // Update the selected option
                            expanded2 = false // Close the dropdown
                            // Call the ViewModel function, stripping "º" for non-numeric values
                            acViewModel.setVerticalSwing(option.replace("º", ""))
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.ac_speed)
        )
        Spacer(modifier = Modifier.height(6.dp))
        // List of options for the dropdown
        val options3 = listOf("auto", "25%", "50%", "75%", "100%")
        // State to track the expanded state of the dropdown
        var expanded3 by remember { mutableStateOf(false) }
        // State to track the currently selected option
        var selectedOption3 by remember { mutableStateOf(acViewModel.uiState.value.currentDevice?.fanSpeed?: options3[0]) } // Default to "Auto"

        ExposedDropdownMenuBox(
            expanded = expanded3,
            onExpandedChange = { expanded3 = it } // Toggle expanded state when clicked
        ) {
            // The text field that shows the currently selected option
            TextField(
                value = selectedOption3,
                onValueChange = {}, // Read-only, so no value change action needed
                readOnly = true, // Prevents user input directly
                trailingIcon = { // Icon indicating dropdown functionality
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded3)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor() // Ensures proper anchoring of the dropdown menu
            )

            // Dropdown menu that shows the list of options
            ExposedDropdownMenu(
                expanded = expanded3,
                onDismissRequest = { expanded3 = false } // Close the dropdown when clicking outside
            ) {
                options3.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) }, // Display the option text
                        onClick = {
                            selectedOption3 = option // Update the selected option
                            expanded3 = false // Close the dropdown
                            // Call the ViewModel function, stripping "º" for non-numeric values
                            acViewModel.setFanSpeed(option.replace("%", ""))
                        }
                    )
                }
            }
        }
    }
    }
    }
}