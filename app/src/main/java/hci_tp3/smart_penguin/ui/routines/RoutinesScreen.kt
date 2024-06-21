package hci_tp3.smart_penguin.ui.routines


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.ui.getViewModelFactory
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp

@Composable
fun RoutinesScreen(
    viewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory()),
) {
    val uiState by viewModel.uiState.collectAsState()
    val routineList = uiState.routines
    if(routineList.isEmpty()){
        NoRoutinesBox()
    }
    else{
        LazyColumn (
            verticalArrangement =  Arrangement.spacedBy(8.dp),
            //contentPadding = PaddingValuesz,
        ) {

            items(items = routineList){ item ->
                RoutineCard(routineName = item.name, routineDescription = item.desc!!)
            }
        }
    }
}

@Preview( showBackground = true,
    device = "id:pixel_3"
)
@Composable
fun RoutinesScreenPreviewMobile(){
    RoutinesScreen()
}
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_tablet")
@Composable
fun RoutinesScreenPreviewTablet(){
    RoutinesScreen()
}