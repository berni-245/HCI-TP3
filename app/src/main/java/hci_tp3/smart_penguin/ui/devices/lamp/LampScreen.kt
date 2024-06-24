package hci_tp3.smart_penguin.ui.devices.lamp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.hexToColor
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.ui.getViewModelFactory
import kotlin.math.roundToInt

@Composable
fun LampScreen(
    lampViewModel: LampViewModel = viewModel(factory = getViewModelFactory()),
) {
    val uiLampUiState by lampViewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(16.dp),
    ) {
        item {
            if (uiLampUiState.currentDevice == null) {
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
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = uiLampUiState.currentDevice?.name ?: "...",
                        fontSize = 22.sp,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    var checked = uiLampUiState.currentDevice?.status == Status.ON
                    if (checked) {
                        Text(
                            text = stringResource(id = R.string.execute_turn_off)
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.execute_turn_on)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            when (uiLampUiState.currentDevice?.status) {
                                Status.ON -> lampViewModel.turnOff()
                                Status.OFF -> lampViewModel.turnOn()
                                else -> {}
                            }
                            checked = !checked
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.lamp_intensity)
                    )
                    var sliderPosition = uiLampUiState.currentDevice!!.brightness
                    Spacer(modifier = Modifier.height(6.dp))
                    Slider(
                        value = sliderPosition.toFloat(),
                        onValueChange = {
                            sliderPosition = it.roundToInt()
                            lampViewModel.setBrightness(sliderPosition)
                        },
                        valueRange = 1f..100f,
                        enabled = checked
                    )
                    Text(text = "$sliderPosition%")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Text(text = stringResource(id = R.string.lamp_color) + ": ")
                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .background(hexToColor(uiLampUiState.currentDevice?.color ?: ""))
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    val controller = rememberColorPickerController()
                    controller.setDebounceDuration(500L)
                    controller.setEnabled(checked)
                    HsvColorPicker(modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .padding(end = LocalConfiguration.current.screenWidthDp.dp / 10,start = LocalConfiguration.current.screenWidthDp.dp / 10),
                        initialColor = hexToColor(uiLampUiState.currentDevice?.color ?: ""),
                        controller = controller,
                        onColorChanged = { colorEnvelope: ColorEnvelope ->
                            if (colorEnvelope.fromUser) {
                                Log.d("Lamp color: ", colorEnvelope.hexCode)
                                val colorString = colorEnvelope.hexCode.substring(2)
                                lampViewModel.setColor(colorString)
                                uiLampUiState.currentDevice?.color = colorString
                            }
                        })
                }
            }
        }
}}