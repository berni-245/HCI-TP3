package hci_tp3.smart_penguin.ui.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.Ac
import hci_tp3.smart_penguin.model.Action
import hci_tp3.smart_penguin.model.Blind
import hci_tp3.smart_penguin.model.DeviceType
import hci_tp3.smart_penguin.model.Lamp
import hci_tp3.smart_penguin.model.Routine
import hci_tp3.smart_penguin.model.Vacuum
import hci_tp3.smart_penguin.model.state.AcMode
import hci_tp3.smart_penguin.model.state.BlindStatus
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.model.state.VacuumMode
import hci_tp3.smart_penguin.model.state.VacuumStatus
import hci_tp3.smart_penguin.ui.theme.HCITP3Theme


@Composable
fun RoutinesInfo(
    routine: Routine
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = routine.name, style = MaterialTheme.typography.headlineLarge)
        RoutinePlayButton {}
        routine.actions.forEach { action ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp)
            ) {

                HandleDeviceType(action = action)
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
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Icon(painter = painterResource(DeviceType.BLIND.icon) , contentDescription = "ac" )
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
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Icon(painter = painterResource(DeviceType.LAMP.icon) , contentDescription = "ac" )
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
   Row (
       verticalAlignment = Alignment.CenterVertically,
       modifier = Modifier.padding(start = 10.dp)
   ) {
       Icon(painter = painterResource(DeviceType.AC.icon) , contentDescription = "ac" )
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
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Icon(painter = painterResource(DeviceType.VACUUM.icon) , contentDescription = "ac" )
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
                Text(text = " : ")
                Text(text = action.params.first())
            }
        }


    }
}


@Composable
fun RoutineInfoScreen(
    routine: Routine
) {

}


@Preview(showBackground = true)
@Composable
fun RoutinesInfoPreview() {
    HCITP3Theme {

        var d1 = Lamp(
            "13",
            "Lamparita",
            status = Status.OFF,
            color = "FFFFFF",
            brightness = 10,
            room = null
        )
        val d2 = Vacuum(id="22","Robocop",null,VacuumStatus.ACTIVE,VacuumMode.MOP,50)
        val d3 = Blind(id="19","Cortina Cocina",null,BlindStatus.CLOSED,0,0)
        val d4 = Ac(id="21","Aire Dormitorio",null,Status.OFF,20,AcMode.FAN,"30","30","50")

        var a6 = Action(device = d3, actionName = "setLevel", params = listOf("15"), null)
        var a7 = Action(device = d4, actionName = "setFanSpeed", params = listOf("100"), null)
        var a8 = Action(device = d2, actionName = "setMode", params = listOf("Mop"), null)
        var a9 = Action(device = d1, actionName = "setColor", params = listOf("768930"), null)
        var actions = listOf(a6,a7,a8,a9)
        var routine = Routine(id = "1234", name = "My Really Cool Routine", actions, null)
        RoutinesInfo(routine = routine)
    }
}

@Preview(showBackground = true, locale = "es")
@Composable
fun RoutinesInfoPreviewEs() {
    HCITP3Theme {

        var d1 = Lamp(
            "13",
            "Lamparita",
            status = Status.OFF,
            color = "FFFFFF",
            brightness = 10,
            room = null
        )
        val d2 = Vacuum(id="22","Robocop",null,VacuumStatus.ACTIVE,VacuumMode.MOP,50)
        val d3 = Blind(id="19","Cortina Cocina",null,BlindStatus.CLOSED,0,0)
        val d4 = Ac(id="21","Aire Dormitorio",null,Status.OFF,20,AcMode.FAN,"30","30","50")

        var a6 = Action(device = d3, actionName = "setLevel", params = listOf("15"), null)
        var a7 = Action(device = d4, actionName = "setFanSpeed", params = listOf("100"), null)
        var a8 = Action(device = d2, actionName = "setMode", params = listOf("Mop"), null)
        var a9 = Action(device = d1, actionName = "setColor", params = listOf("FFF000"), null)
        var actions = listOf(a6,a7,a8,a9)
        var routine = Routine(id = "1234", name = "Mi Rutina", actions, null)
        RoutinesInfo(routine = routine)
    }
}