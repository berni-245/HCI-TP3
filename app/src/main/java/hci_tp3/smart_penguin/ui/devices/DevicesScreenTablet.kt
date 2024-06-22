package hci_tp3.smart_penguin.ui.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.Device
import hci_tp3.smart_penguin.model.DeviceType
import hci_tp3.smart_penguin.ui.getViewModelFactory
import hci_tp3.smart_penguin.ui.navigation.AppDestinations

class ButtonViewModel : ViewModel() {
    var selectedButton by mutableStateOf(DeviceType.LAMP)
}

@Composable
fun DevicesScreenTablet(
    viewModel: DevicesViewModel = viewModel(factory = getViewModelFactory()),
    buttonViewModel: ButtonViewModel = viewModel(),
    onNavigateDestination: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState.isFetching)
    val sizeMultiplier: Float =
        if (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) 1F else 0.8F
    Row {
        Box(
            modifier = Modifier
                .width((300 * sizeMultiplier).dp)
                .background(colorScheme.onBackground)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {
                DeviceTypeButton(DeviceType.LAMP, buttonViewModel)
                DeviceTypeButton(DeviceType.AC, buttonViewModel)
                DeviceTypeButton(DeviceType.BLIND, buttonViewModel)
                DeviceTypeButton(DeviceType.VACUUM, buttonViewModel)
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SwipeRefresh(state = swipeRefreshState, onRefresh = viewModel::getDevices) {
                when (buttonViewModel.selectedButton) {
                    DeviceType.LAMP -> DeviceSectionTablet(
                        viewModel,
                        DeviceType.LAMP,
                        uiState.lamps,
                        onNavigateDestination
                    )

                    DeviceType.BLIND -> DeviceSectionTablet(
                        viewModel,
                        DeviceType.BLIND,
                        uiState.blinds,
                        onNavigateDestination
                    )

                    DeviceType.AC -> DeviceSectionTablet(
                        viewModel,
                        DeviceType.AC,
                        uiState.acs,
                        onNavigateDestination
                    )

                    DeviceType.VACUUM -> DeviceSectionTablet(
                        viewModel,
                        DeviceType.VACUUM,
                        uiState.vacuums,
                        onNavigateDestination
                    )
                }
            }
        }
    }
}

@Composable
fun DeviceTypeButton(deviceType: DeviceType, buttonViewModel: ButtonViewModel) {
    Button(
        onClick = {
            buttonViewModel.selectedButton = deviceType
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(150.dp)
            .height(60.dp)
    ) {
        Text(deviceType.getString())
    }
}

@Composable
fun DeviceSectionTablet(
    viewModel: DevicesViewModel,
    deviceType: DeviceType,
    devices: List<Device>,
    onNavigateDestination: (String) -> Unit
) {
    val sizeMultiplier: Float =
        if (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) 1F else 0.8F
    val title = deviceType.getString()
    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp),
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = title, fontSize = 40.sp, fontWeight = FontWeight.Medium)
            Icon(
                painter = painterResource(id = deviceType.icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        if (devices.isEmpty()) {
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
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = (200 * sizeMultiplier).dp),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                contentPadding = PaddingValues(vertical = 20.dp, horizontal = 50.dp),
            ) {
                items(devices) { device ->
                    DeviceItem(
                        viewModel = viewModel,
                        device = device,
                        sizeMultiplier = sizeMultiplier,
                        onNavigateDestination = onNavigateDestination
                    )
                }
            }
        }
    }
}

@Composable
fun DeviceItem(viewModel: DevicesViewModel, device: Device, sizeMultiplier: Float, onNavigateDestination: (String) -> Unit) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .clip(RoundedCornerShape(8.dp))
            .background(colorScheme.primaryContainer)
            .size((200 * sizeMultiplier).dp)
            .padding(10.dp)
            .clickable {
                viewModel.setDevice(device)
                onNavigateDestination(AppDestinations.LAMP.route)
            },
        contentAlignment = Alignment.TopStart, // Align content to the top start
    ) {
        Row {
            Text(
                text = device.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold, // Make text bold
            )
        }
    }
}
