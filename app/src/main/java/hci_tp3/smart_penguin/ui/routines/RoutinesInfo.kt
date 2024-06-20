package hci_tp3.smart_penguin.ui.routines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hci_tp3.smart_penguin.model.Action
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
                Text(text = action.device.name,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                    )
                Row (
                    modifier = Modifier.padding(start = 36.dp)
                ) {
                    Text(text = action.actionName)
                    if(action.params.isNotEmpty()){
                        Text(text = " : ")
                        action.params.forEach { param ->
                            Text(text = param)
                        }
                    }
                }

            }

        }


   }
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
        var a1 = Action(device = d1,actionName = "TurnOn",params = listOf("hola"),null)
        var actions = listOf(a1)
        var routine = Routine(id= "1234", name= "Mi Rutina Genial",actions,null)
        RoutinesInfo(routine = routine)
    }
}