package hci_tp3.smart_penguin.ui.devices.vacuum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.state.VacuumMode
import hci_tp3.smart_penguin.model.state.VacuumStatus
import hci_tp3.smart_penguin.ui.getViewModelFactory
import hci_tp3.smart_penguin.ui.navigation.AppDestinations
import hci_tp3.smart_penguin.ui.theme.HCITP3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacuumScreen(
    viewModel: VacuumViewModel = viewModel(factory = getViewModelFactory()),
    onNavigateDestination: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    if (uiState.currentDevice == null) {
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
            modifier = Modifier
                .padding(16.dp),
        ) {
            // Header with title and icons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = uiState.currentDevice!!.name,
                    fontSize = 22.sp,
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Control buttons
            Button(
                onClick = {
                    if (uiState.currentDevice?.status == VacuumStatus.ACTIVE) viewModel.pause()
                    else viewModel.start()
                },
                enabled = uiState.isThereBatteryLeft
            ) {
                Icon(
                    if (uiState.currentDevice?.status == VacuumStatus.ACTIVE) Icons.Default.Pause
                    else Icons.Default.PlayArrow,
                    contentDescription = if (uiState.currentDevice?.status == VacuumStatus.ACTIVE) "Pause" else "Play"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            SingleChoiceSegmentedCard(
                choices = listOf(VacuumMode.VACUUM.getString(), VacuumMode.MOP.getString()),
                selectedChoice = uiState.currentDevice!!.mode.getString(),
                onChoiceSelected = { /* TODO: change mode */}
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Room selection dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = uiState.currentDevice!!.room?.name ?: "aaa",
                    onValueChange = {
                    },
                    readOnly = true,
                    enabled = uiState.isThereBatteryLeft,
                    label = { Text(stringResource(R.string.Room)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = LocalConfiguration.current.screenWidthDp.dp / 5)
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    uiState.rooms.forEach { room ->
                        DropdownMenuItem(
                            text = { Text(text = room.name) },
                            onClick = {
                                viewModel.setLocation(room.id!!)
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Base button
            ElevatedCard(
                onClick = { viewModel.dock() },
                modifier = Modifier.size(64.dp) // Adjust size as needed
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (uiState.currentDevice!!.status == VacuumStatus.DOCKED)
                        Text(text = stringResource(R.string.in_base))
                    else
                        Text(text = stringResource(R.string.go_to_base))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(R.string.battery) + uiState.currentDevice!!.batteryLevel.toString() + "%")
            if (!uiState.isThereBatteryLeft) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(R.string.low_battery))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Close button
//            Button(onClick = { onNavigateDestination(AppDestinations.DEVICES.route) }) {
//                Text(text = stringResource(R.string.close_blind_action))
//            }
        }
    }
}

@Composable
fun SingleChoiceSegmentedCard(
    choices: List<String>,
    selectedChoice: String,
    onChoiceSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = LocalConfiguration.current.screenWidthDp.dp / 4),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        choices.forEach { choice ->
            val isSelected = selectedChoice == choice
            ElevatedCard(
                onClick = { onChoiceSelected(choice) },
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        text = choice,
                    )
                }
            }
        }
    }
}