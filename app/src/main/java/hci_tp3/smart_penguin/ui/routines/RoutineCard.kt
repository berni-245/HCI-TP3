package hci_tp3.smart_penguin.ui.routines


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign


@Composable
fun RoutineCard(
  routineName : String,
  routineDescription : String
){
    ElevatedCard(
        onClick = { /*luego*/ },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = routineName,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineSmall)

                Text(text = routineDescription,
                    modifier = Modifier
                        .padding(start = 16.dp),
                    textAlign = TextAlign.Center,
                    )
            }
            RoutinePlayButton {

            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel")
@Composable
fun RoutineCardPreview(){
    Column{
    RoutineCard(routineName = "Berni's Routine", routineDescription = "Rutina de Berna para hacer cafe")
        RoutineCard(routineName = "Berni's Routine", routineDescription = "Rutina de Berna para hacer cafe")

        RoutineCard(routineName = "Berni's Routine", routineDescription = "Rutina de Berna para hacer cafe")

        RoutineCard(routineName = "Berni's Routine", routineDescription = "Rutina de Berna para hacer cafe")

        RoutineCard(routineName = "Berni's Routine", routineDescription = "Rutina de Berna para hacer cafe")

    }
}