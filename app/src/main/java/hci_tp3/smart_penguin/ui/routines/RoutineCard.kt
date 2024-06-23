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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.model.Routine
import hci_tp3.smart_penguin.ui.getViewModelFactory
import hci_tp3.smart_penguin.ui.navigation.AppDestinations


@Composable
fun RoutineCard(
    routine: Routine,
    onNavigateDestination: (String) -> Unit,
    viewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory()),
) {
    var name = routine.name
    if(name.length > 13){
        name = name.substring(0,13).plus("..")
    }
    ElevatedCard(
        onClick = {
            viewModel.setCurrentRoutine(routine)
            onNavigateDestination(AppDestinations.CURRENT_ROUTINE.route)
        },
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
                Text(
                    text = name,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = routine.desc!!,
                    modifier = Modifier
                        .padding(start = 16.dp),
                    textAlign = TextAlign.Center,
                )
            }
            routine.id?.let { RoutinePlayButton (it,viewModel) }
        }

    }
}
