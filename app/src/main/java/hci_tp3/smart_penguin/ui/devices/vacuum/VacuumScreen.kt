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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.model.state.VacuumMode
import hci_tp3.smart_penguin.ui.getViewModelFactory
import hci_tp3.smart_penguin.ui.theme.HCITP3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacuumScreen(
//    viewModel: VacuumViewModel = viewModel(factory = getViewModelFactory()),
//    vacuumMode: VacuumMode
) {
//    val uiState by viewModel.uiState.collectAsState()
    var isPlaying by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var selectedRoom by remember { mutableStateOf("Living Room") }
    val rooms = listOf("Living Room", "Bedroom", "Kitchen", "Bathroom")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with title and icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Aspiradora de Tobi", fontSize = 22.sp, style = MaterialTheme.typography.headlineMedium, modifier = Modifier)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Control buttons
        Button(onClick = { isPlaying = !isPlaying }) {
            Icon(if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow, contentDescription = if (isPlaying) "Pause" else "Play")
        }

        Spacer(modifier = Modifier.height(16.dp))

        SingleChoiceSegmentedCard(
            choices = listOf(VacuumMode.VACUUM.getString(), VacuumMode.MOP.getString()),
            selectedChoice = VacuumMode.VACUUM.getString(),
            onChoiceSelected = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Room selection dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedRoom,
                onValueChange = {},
                readOnly = true,
                label = { Text("Sala") },
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
                rooms.forEach { room ->
                    DropdownMenuItem(
                        text = { Text(text = room) },
                        onClick = {
                            selectedRoom = room
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Base button
        Button(
            onClick = { /* Send to base action */ },
            enabled = true
        ) {
            Text(text = "EN BASE")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Close button
        Button(onClick = { /* Close action */ }) {
            Text(text = "CERRAR")
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
                        .padding(16.dp)
                ) {
                    Text(
                        text = choice,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VacuumScreenPreview() {
    HCITP3Theme {
        VacuumScreen()
    }
}
