package hci_tp3.smart_penguin.ui.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.ui.component.ActionButton
import hci_tp3.smart_penguin.ui.devices.lamp.LampViewModel
import hci_tp3.smart_penguin.ui.devices.lamp.canExecuteAction

import hci_tp3.smart_penguin.model.Device
import hci_tp3.smart_penguin.model.DeviceType
import hci_tp3.smart_penguin.ui.getViewModelFactory

@Composable
fun DevicesScreen(
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsState()
    var asdf = 1234
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
//      if (uiS) DeviceSection(DeviceType.LAMP, uiState.lamps)
//      if (acs.isNotEmpty()) DeviceSection(DeviceType.AC, acs)
//      if (blinds.isNotEmpty()) DeviceSection(DeviceType.BLIND, blinds)
//      if (vacuums.isNotEmpty()) DeviceSection(DeviceType.VACUUM, vacuums)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DeviceSection(deviceType: DeviceType, devices: Array<out Device>) {
    val title = deviceType.getString()
    val colors = MaterialTheme.colorScheme
    Column {
        Row {
          Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Medium)
          Spacer(modifier = Modifier.height(4.dp))
          deviceType.icon?.let { icon ->
              Icon(
                  painter = painterResource(id = icon),
                  contentDescription = null,
                  modifier = Modifier.size(24.dp)
              )
          }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(thickness = 2.dp, color = colors.tertiary)
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            devices.forEach { device ->
              Box(
                modifier = Modifier
                  .size(100.dp)
                  .padding(8.dp)
                  .background(colors.primaryContainer, shape = RoundedCornerShape(8.dp))
              ) {
                Text(text = device.name, fontSize = 16.sp)
              }
            }
        }
    }
}
