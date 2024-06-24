package hci_tp3.smart_penguin.ui.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.Ac
import hci_tp3.smart_penguin.model.Action
import hci_tp3.smart_penguin.model.Blind
import hci_tp3.smart_penguin.model.DeviceType
import hci_tp3.smart_penguin.model.Lamp
import hci_tp3.smart_penguin.model.Vacuum
import hci_tp3.smart_penguin.ui.getViewModelFactory


@Composable
fun RoutinesInfo(
    viewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory()),
) {
//    val routineState by viewModel.uiState.collectAsState()
//    val routine = routineState.currentRoutine

    val routine = viewModel.getCurrentRoutine()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        item{
            Text(text = routine?.name ?: "...", style = MaterialTheme.typography.headlineLarge)
            if (routine != null) {
                routine.id?.let { RoutinePlayButton (it,viewModel) }
            }
            routine?.actions?.forEach { action ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(start = 8.dp, end = 16.dp, top = 10.dp, bottom = 8.dp)
                ) {
                    HandleDeviceType(action = action)
                }

            }
        }
    }


}

@Composable
fun HandleDeviceType(action: Action) {

    when (action.device.type) {
        DeviceType.BLIND -> BlindHandler(action)
        DeviceType.LAMP -> LampHandler(action)
        DeviceType.AC -> AcHandler(action)
        DeviceType.VACUUM -> VacuumHandler(action)
    }


}

@Composable
fun PrintActionName(name: String) {
    Text(
        text = name,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold
        )
    )
}


@Composable
fun BlindHandler(action: Action) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Icon(painter = painterResource(DeviceType.BLIND.icon), contentDescription = "ac")
        PrintActionName(action.device.name)
    }

    Row(
        modifier = Modifier.padding(start = 30.dp)
    ) {
        when (action.actionName) {
            Blind.OPEN_ACTION -> {
                Text(stringResource(R.string.open_blinds_action))
            }

            Blind.CLOSE_ACTION -> {
                Text(stringResource(R.string.close_blind_action))
            }

            Blind.SET_LEVEL_ACTION -> {
                Text(stringResource(R.string.set_level_blind_action))
                Text(text = " : ")
                Text(text = action.params.first() + "%")
            }
        }
    }
}


@Composable
fun LampHandler(action: Action) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Icon(painter = painterResource(DeviceType.LAMP.icon), contentDescription = "ac")
        PrintActionName(action.device.name)
    }


    Row(
        modifier = Modifier.padding(start = 30.dp)
    ) {
        when (action.actionName) {
            Lamp.SET_COLOR_ACTION -> {
                Text(stringResource(R.string.set_color_lamp_action))
                Text(text = " : ")
                val color = android.graphics.Color.parseColor("#${action.params.first()}")
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .background(color = Color(color)) // Specify the desired background color here
                ) {}
            }

            Lamp.SET_BRIGHTNESS_ACTION -> {
                Text(stringResource(R.string.set_brightness_lamp_action))
                Text(text = " : ")
                Text(text = action.params.first() + "%")
            }

            Lamp.TURN_ON_ACTION -> {
                Text(stringResource(R.string.turn_on_lamp_action))
            }

            Lamp.TURN_OFF_ACTION -> {
                Text(stringResource(R.string.turn_off_lamp_action))
            }
        }
    }
}

@Composable
fun AcHandler(action: Action) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Icon(painter = painterResource(DeviceType.AC.icon), contentDescription = "ac")
        PrintActionName(action.device.name)
    }

    Row(
        modifier = Modifier.padding(start = 30.dp)
    ) {
        when (action.actionName) {
            Ac.TURN_ON_ACTION -> {
                Text(stringResource(R.string.turn_on_lamp_action))
            }

            Ac.TURN_OFF_ACTION -> {
                Text(stringResource(R.string.turn_off_lamp_action))
            }

            Ac.SET_MODE_ACTION -> {
                Text(stringResource(R.string.set_mode_ac_vacuum_action))
                Text(text = " : ")
                Text(text = action.params.first())
            }

            Ac.SET_FAN_SPEED_ACTION -> {
                Text(stringResource(R.string.set_fan_speed_ac_action))
                Text(text = " : ")
                Text(text = action.params.first() + "%")
            }

            Ac.SET_TEMPERATURE_ACTION -> {
                Text(stringResource(R.string.set_temp_ac_action))
                Text(text = " : ")
                Text(text = action.params.first() + "°")
            }

            Ac.SET_HORIZONTAL_SWING_ACTION -> {
                Text(stringResource(R.string.set_horz_swing_ac))
                Text(text = " :")
                Text(text = action.params.first() + "°")
            }

            Ac.SET_VERTICAL_SWING_ACTION -> {
                Text(stringResource(R.string.set_vert_swing_ac_action))
                Text(text = " :")
                Text(text = action.params.first() + "°")
            }
        }
    }
}

@Composable
fun VacuumHandler(action: Action) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Icon(painter = painterResource(DeviceType.VACUUM.icon), contentDescription = "ac")
        PrintActionName(action.device.name)
    }
    Row(
        modifier = Modifier.padding(start = 30.dp)
    ) {
        when (action.actionName) {
            Vacuum.SET_MODE_ACTION -> {
                Text(stringResource(R.string.set_mode_ac_vacuum_action))
                Text(text = " : ")
                Text(text = action.params.first())
            }

            Vacuum.DOCK_ACTION -> {
                Text(stringResource(R.string.dock_vacuum_action))
            }

            Vacuum.PAUSE_ACTION -> {
                Text(stringResource(R.string.pause_vacuum_action))
            }

            Vacuum.START_ACTION -> {
                Text(stringResource(R.string.start_vacuum_action))
            }

            Vacuum.SET_LOCATION_ACTION -> {
                Text(stringResource(R.string.set_dock_room_vacuum_action))
                /*Text(text = " : ")
                Text(text = action.params.first())*/
            }
        }
    }
}