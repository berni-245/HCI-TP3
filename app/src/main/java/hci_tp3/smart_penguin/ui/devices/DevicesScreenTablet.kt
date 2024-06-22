package hci_tp3.smart_penguin.ui.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import hci_tp3.smart_penguin.ui.theme.HCITP3Theme

@Composable
fun DevicesScreenTablet(
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
) {
    val uiState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState.isFetching)
    SwipeRefresh(state = swipeRefreshState, onRefresh = viewModel::getDevices) {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            if (uiState.lamps.isNotEmpty()) item {
                DeviceSectionTablet(
                    DeviceType.LAMP,
                    uiState.lamps
                )
            }
            if (uiState.acs.isNotEmpty()) item { DeviceSectionTablet(DeviceType.AC, uiState.acs) }
            if (uiState.blinds.isNotEmpty()) item {
                DeviceSectionTablet(
                    DeviceType.BLIND,
                    uiState.blinds
                )
            }
            if (uiState.vacuums.isNotEmpty()) item {
                DeviceSectionTablet(
                    DeviceType.VACUUM,
                    uiState.vacuums
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
fun DeviceSectionTablet(deviceType: DeviceType, devices: List<Device>) {
    val title = deviceType.getString()
    HCITP3Theme {
        Column {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(text = title + "Hola", fontSize = 20.sp, fontWeight = FontWeight.Medium)
                deviceType.icon?.let { icon ->
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(thickness = 2.dp, color = colorScheme.tertiary)
            Spacer(modifier = Modifier.height(20.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
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
                            .size(200.dp)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = device.name, fontSize = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }

    }
}
