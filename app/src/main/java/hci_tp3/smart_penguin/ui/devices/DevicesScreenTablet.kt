package hci_tp3.smart_penguin.ui.devices

import android.annotation.SuppressLint
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.hexToColor
import hci_tp3.smart_penguin.model.Ac
import hci_tp3.smart_penguin.model.Blind
import hci_tp3.smart_penguin.model.Device
import hci_tp3.smart_penguin.model.DeviceType
import hci_tp3.smart_penguin.model.Lamp
import hci_tp3.smart_penguin.model.Vacuum
import hci_tp3.smart_penguin.model.state.AcMode
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.model.state.VacuumStatus
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
                        deviceCard = LampCard(),
                        onNavigateDestination
                    )

                    DeviceType.BLIND -> DeviceSectionTablet(
                        viewModel,
                        DeviceType.BLIND,
                        uiState.blinds,
                        deviceCard = BlindCard(),
                        onNavigateDestination
                    )

                    DeviceType.AC -> DeviceSectionTablet(
                        viewModel,
                        DeviceType.AC,
                        uiState.acs,
                        deviceCard = AcCard(),
                        onNavigateDestination
                    )

                    DeviceType.VACUUM -> DeviceSectionTablet(
                        viewModel,
                        DeviceType.VACUUM,
                        uiState.vacuums,
                        deviceCard = VacuumCard(),
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
fun <T : Device> DeviceSectionTablet(
    viewModel: DevicesViewModel,
    deviceType: DeviceType,
    devices: List<T>,
    deviceCard: DeviceCard<T>,
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
                    deviceCard.DeviceCard(
                        device = device,
                        viewModel = viewModel,
                        sizeMultiplier = sizeMultiplier,
                        onNavigateDestination = onNavigateDestination
                    )
                }
            }
        }
    }
}

@Composable
fun DeviceItem(
    modifier: Modifier,
    device: Device,
    sizeMultiplier: Float,
    deviceInfo: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .size((200 * sizeMultiplier).dp)
            .padding(10.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Column {
            Text(
                text = device.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            deviceInfo()
        }
    }
}

interface DeviceCard<T : Device> {
    @SuppressLint("NotConstructor")
    @Composable
    fun DeviceCard(
        device: T,
        viewModel: DevicesViewModel,
        sizeMultiplier: Float,
        onNavigateDestination: (String) -> Unit
    )
}

class LampCard : DeviceCard<Lamp> {
    @Composable
    override fun DeviceCard(
        device: Lamp,
        viewModel: DevicesViewModel,
        sizeMultiplier: Float,
        onNavigateDestination: (String) -> Unit,
    ) {
        var modifier = Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .background(colorScheme.primaryContainer)
        val lampColor = hexToColor(device.color)
        val colors: List<Color>
        if (device.status == Status.ON) {
            colors = listOf(
                lampColor,
                lampColor.copy(alpha = 0.7f),
                lampColor.copy(alpha = 0.5f),
                lampColor.copy(alpha = 0.3f),
                lampColor.copy(alpha = 0.1f),
                Color.Transparent
            )
        } else {
            colors = listOf(
                lampColor.copy(alpha = 0.4f),
                lampColor.copy(alpha = 0.2f),
                lampColor.copy(alpha = 0.1f),
                Color.Transparent
            )
        }
        modifier = modifier.then(
            Modifier
                .background(
                    brush = Brush.radialGradient(
                        colors = colors,
                        center = Offset(Float.POSITIVE_INFINITY, 0f),
                        radius = 500f
                    )
                )
        )
        DeviceItem(
            device = device,
            sizeMultiplier = sizeMultiplier,
            modifier = modifier
                .clickable {
                    viewModel.setDevice(device)
                    onNavigateDestination(AppDestinations.LAMP.route)
                },
            deviceInfo = {
                Column {
                    Text(text = device.status.getString())
                    Text(
                        text = stringResource(id = R.string.lamp_intensity) + ": " + device.brightness + "%"
                    )
                }
            }
        )
    }
}

class BlindCard : DeviceCard<Blind> {
    @Composable
    override fun DeviceCard(
        device: Blind,
        viewModel: DevicesViewModel,
        sizeMultiplier: Float,
        onNavigateDestination: (String) -> Unit,
    ) {
        DeviceItem(
            device = device,
            sizeMultiplier = sizeMultiplier,
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp),
                )
                .background(colorScheme.primaryContainer)
                .drawBehind {
                    val rectangleWidth = size.width
                    val rectangleHeight = size.height * device.currentLevel / 100

                    val color = Color.Gray.copy(alpha = 0.5f)

                    drawRect(
                        color = color,
                        size = Size(rectangleWidth, rectangleHeight)
                    )
                }
                .clickable {
                    viewModel.setDevice(device)
                    onNavigateDestination(AppDestinations.BLIND.route)
                },
            deviceInfo = {
                Column {
                    Text(text = device.status.getString())
                }
            }
        )
    }
}

class AcCard : DeviceCard<Ac> {
    @Composable
    override fun DeviceCard(
        device: Ac,
        viewModel: DevicesViewModel,
        sizeMultiplier: Float,
        onNavigateDestination: (String) -> Unit,
    ) {
        val modeStringColor: String
        val modeIcon: Int
        when (device.mode) {
            AcMode.HEAT -> {
                modeStringColor = "#fa3f00"
                modeIcon = R.drawable.ac_mode_heat
            }

            AcMode.COOL -> {
                modeStringColor = "#405be3"
                modeIcon = R.drawable.ic_ac
            }

            AcMode.FAN -> {
                modeStringColor = "#5891d6AA"
                modeIcon = R.drawable.ac_mode_fan
            }
        }
        val modeColor = Color(android.graphics.Color.parseColor(modeStringColor))
        DeviceItem(
            device = device,
            sizeMultiplier = sizeMultiplier,
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp),
                )
                .background(colorScheme.primaryContainer)
                .then(
                    if (device.status == Status.ON) {
                        Modifier.background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    modeColor,
                                    modeColor.copy(alpha = 0.7f),
                                    modeColor.copy(alpha = 0.5f),
                                    modeColor.copy(alpha = 0.3f),
                                    modeColor.copy(alpha = 0.1f),
                                    Color.Transparent
                                ),
                                center = Offset(Float.POSITIVE_INFINITY, 200f * sizeMultiplier),
                                radius = 800f * sizeMultiplier
                            )
                        )
                    } else {
                        Modifier
                    }
                )
                .size((250 * sizeMultiplier).dp)
                .clickable {
                    viewModel.setDevice(device)
                    onNavigateDestination(AppDestinations.AC.route)
                },
            deviceInfo = {
                Column {
                    Row {
                        Text(text = device.status.getString())
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            painter = painterResource(id = modeIcon),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                    Text(text = device.temperature.toString() + "°C")
                    Text(text = stringResource(R.string.ac_h_swing) + ": " + device.horizontalSwing)
                    Text(text = stringResource(R.string.ac_v_swing) + ": " + device.verticalSwing)
                    Text(text = stringResource(R.string.ac_speed) + ": " + device.fanSpeed)
                }
            }
        )
    }
}

class VacuumCard : DeviceCard<Vacuum> {
    @Composable
    override fun DeviceCard(
        device: Vacuum,
        viewModel: DevicesViewModel,
        sizeMultiplier: Float,
        onNavigateDestination: (String) -> Unit,
    ) {
        DeviceItem(
            device = device,
            sizeMultiplier = sizeMultiplier,
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp),
                )
                .background(colorScheme.primaryContainer)
                .clickable {
                    viewModel.setDevice(device)
                    onNavigateDestination(AppDestinations.VACUUM.route)
                },
            deviceInfo = {
                val mode = if (device.status == VacuumStatus.ACTIVE) ", " + device.mode.getString() else ""
                Column {
                    Text(text = device.status.getString() + mode)
                    Text(device.location?.name ?: stringResource(R.string.no_room_asigned))
                    Text(text = stringResource(R.string.battery) + ": " + device.batteryLevel.toString() + "%")
                }
            }
        )
    }
}