package hci_tp3.smart_penguin.ui.devices.vacuum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.state.VacuumMode
import hci_tp3.smart_penguin.model.state.VacuumStatus
import hci_tp3.smart_penguin.ui.getViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacuumScreen(
    viewModel: VacuumViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    uiState.currentDevice?.let { currentDevice ->
        LazyColumn(
            modifier = Modifier
                .padding(16.dp),
        ) {
            item {
                // Header with title and icons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = currentDevice.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Control buttons
                Button(
                    onClick = {
                        if (currentDevice.status == VacuumStatus.ACTIVE) viewModel.pause()
                        else viewModel.start()
                    },
                    enabled = uiState.isThereBatteryLeft && uiState.isThereARoom
                ) {
                    Icon(
                        if (currentDevice.status == VacuumStatus.ACTIVE) Icons.Default.Pause
                        else Icons.Default.PlayArrow,
                        contentDescription = if (currentDevice.status == VacuumStatus.ACTIVE) "Pause" else "Play"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                SingleChoiceSegmentedCard(
                    choices = listOf(VacuumMode.VACUUM, VacuumMode.MOP),
                    selectedChoice = currentDevice.mode,
                    onChoiceSelected = { mode ->
                        viewModel.setMode(mode)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Room selection dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded && uiState.isThereARoom,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = currentDevice.location?.name ?: stringResource(R.string.no_room_asigned),
                        onValueChange = { },
                        readOnly = true,
                        enabled = uiState.isThereARoom,
                        label = { Text(stringResource(R.string.current_room)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = LocalConfiguration.current.screenWidthDp.dp / 5)
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded && uiState.isThereARoom,
                        onDismissRequest = { expanded = false }
                    ) {
                        uiState.rooms.forEach { room ->
                            DropdownMenuItem(
                                text = { Text(text = room.name) },
                                onClick = {
                                    viewModel.setLocation(room.id!!)
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Base button
                ElevatedCard(
                    onClick = { viewModel.dock() },
                    modifier = Modifier,
                    enabled = uiState.isThereARoom
                ) {
                    Box(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        if (currentDevice.status == VacuumStatus.DOCKED)
                            Text(text = stringResource(R.string.in_base))
                        else
                            Text(text = stringResource(R.string.go_to_base))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = stringResource(R.string.battery) + currentDevice.batteryLevel.toString() + "%")
                if (!uiState.isThereBatteryLeft) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.low_battery), color = Color.Red)
                }
                if (!uiState.isThereARoom) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.no_room_linked), color = Color.Red)
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    } ?: run {
        // Mostrar un indicador de carga mientras los datos se están obteniendo
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun SingleChoiceSegmentedCard(
    choices: List<VacuumMode>,
    selectedChoice: VacuumMode,
    onChoiceSelected: (VacuumMode) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = LocalConfiguration.current.screenWidthDp.dp / 4),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        choices.forEach { choice ->
            ElevatedCard(
                onClick = { onChoiceSelected(choice) },
                modifier = Modifier
                    .weight(1f)
                    .background(if (choice == selectedChoice) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                    .padding(1.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(11.dp)
                ) {
                    Text(
                        text = choice.getString()
                    )
                }
            }
        }
    }
}
