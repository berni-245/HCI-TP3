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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hci_tp3.smart_penguin.model.Action
import hci_tp3.smart_penguin.model.DeviceType
import hci_tp3.smart_penguin.model.Lamp
import hci_tp3.smart_penguin.model.Routine
import hci_tp3.smart_penguin.model.state.Status
import hci_tp3.smart_penguin.ui.theme.HCITP3Theme


@Composable
fun RoutinesInfo(
    routine : Routine
){
   Column (
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Top,
       modifier = Modifier.fillMaxSize()
   ){
        Text(text = routine.name ,  style = MaterialTheme.typography.headlineLarge)
        RoutinePlayButton {}
        routine.actions.forEach { action  ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(16.dp)
            ){

                HandleDeviceType(action = action)
            }

        }


   }
}

@Composable
fun HandleDeviceType(action : Action){

    when(action.device.type){
        DeviceType.BLIND -> BlindHandler(action)
        DeviceType.LAMP -> LampHandler(action)
        DeviceType.AC -> AcHandler(action)
        DeviceType.VACUUM -> VacuumHandler(action)
    }


}

@Composable
fun PrintActionName(name : String){
    Text(text = name,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold
        )
    )
}


@Composable
fun BlindHandler(action : Action){
   /* TODO Icon(painter = , contentDescription = )*/
    PrintActionName(action.actionName)
}


@Composable
fun LampHandler(action : Action){
    /* TODO Icon(painter = , contentDescription = )*/
    PrintActionName(action.device.name)
    Row (
        modifier = Modifier.padding(start = 36.dp)
    ) {
        Text(text = action.actionName)

        if(action.params.isNotEmpty()){
            Text(text = " : ")
            when(action.actionName){
                Lamp.SET_COLOR_ACTION -> {
                    val color =  android.graphics.Color.parseColor("#${action.params.first()}")
                    Box(
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .background(color = Color(color)) // Specify the desired background color here
                    ) {}
                }
                Lamp.SET_BRIGHTNESS_ACTION ->{
                    Text(text = action.params.first())
                }
                else -> {

                }
            }

        }
    }
}

@Composable
fun AcHandler(action : Action){
    /* TODO Icon(painter = , contentDescription = )*/
    PrintActionName(action.actionName)
}

@Composable
fun VacuumHandler(action : Action){
    /* TODO Icon(painter = , contentDescription = )*/
    PrintActionName(action.actionName)
}







@Composable
fun RoutineInfoScreen(
    routine : Routine
){

}


@Preview(showBackground = true)
@Composable
fun RoutinesInfoPreview(){
    HCITP3Theme {

        var d1 = Lamp("12","Lamparita",status = Status.OFF, color = "FFFFFF", brightness = 10, room = null)
        var a1 = Action(device = d1,actionName = "setColor",params = listOf("3068FF"),null)
        var a2 = Action(device = d1,actionName = "setBrightness",params = listOf("60"),null)
        var a3 = Action(device = d1,actionName = "turnOn",params = emptyList(),null)
        var a4 = Action(device = d1,actionName = "turnOff",params = emptyList(),null)
        var actions = listOf(a1,a2,a3,a4)
        var routine = Routine(id= "1234", name= "Mi Rutina Genial",actions,null)
        RoutinesInfo(routine = routine)
    }
}