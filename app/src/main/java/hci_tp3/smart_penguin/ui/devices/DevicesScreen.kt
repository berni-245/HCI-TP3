package hci_tp3.smart_penguin.ui.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.Device
import hci_tp3.smart_penguin.model.DeviceType
import hci_tp3.smart_penguin.ui.getViewModelFactory
import hci_tp3.smart_penguin.ui.navigation.AppDestinations

@Composable
fun DevicesScreen(
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
    onNavigateDestination: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState.isFetching)
    SwipeRefresh(state = swipeRefreshState, onRefresh = viewModel::getDevices) {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            if (uiState.lamps.isNotEmpty()) item {
                DeviceSection(
                    DeviceType.LAMP, uiState.lamps, onNavigateDestination = onNavigateDestination
                )
            }
            if (uiState.acs.isNotEmpty()) item {
                DeviceSection(
                    DeviceType.AC, uiState.acs, onNavigateDestination = onNavigateDestination
                )
            }
            if (uiState.blinds.isNotEmpty()) item {
                DeviceSection(
                    DeviceType.BLIND, uiState.blinds, onNavigateDestination = onNavigateDestination
                )
            }
            if (uiState.vacuums.isNotEmpty()) item {
                DeviceSection(
                    DeviceType.VACUUM,
                    uiState.vacuums,
                    onNavigateDestination = onNavigateDestination
                )
            }
            if (!uiState.hasDevices) {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_no_devices),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(500.dp),
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DeviceSection(
    deviceType: DeviceType,
    devices: List<Device>,
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
    onNavigateDestination: (String) -> Unit
) {
    val title = deviceType.getString()
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Icon(
                painter = painterResource(id = deviceType.icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(
            thickness = 2.dp, color = colorScheme.tertiary, modifier = Modifier.fillMaxWidth(0.7F)
        )
        Spacer(modifier = Modifier.height(20.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            devices.forEach { device ->
                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(8.dp),
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .background(colorScheme.primaryContainer)
                        .size(100.dp)
                        .padding(10.dp)
                        .clickable {
                            viewModel.setDevice(device)
                            when (device.type) {
                                DeviceType.AC -> onNavigateDestination(AppDestinations.AC.route)
                                DeviceType.LAMP -> onNavigateDestination(AppDestinations.LAMP.route)
                                DeviceType.BLIND -> onNavigateDestination(AppDestinations.BLIND.route)
                                DeviceType.VACUUM -> onNavigateDestination(AppDestinations.VACUUM.route)
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = device.name, fontSize = 16.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}
