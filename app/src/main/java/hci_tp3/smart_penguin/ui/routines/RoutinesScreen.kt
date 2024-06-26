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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun RoutinesScreen(
    onNavigateDestination: (String) -> Unit,
    viewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory()),
) {
    val uiState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState.loading)
    val routineList = uiState.routines
    SwipeRefresh(state = swipeRefreshState, onRefresh = viewModel::getRoutines) {
        if (routineList.isEmpty()) {
            NoRoutinesBox()
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(items = routineList) { routine ->
                    RoutineCard(routine, onNavigateDestination)
                }
            }
        }
    }
}

//@Preview( showBackground = true,
//    device = "id:pixel_3"
//)
//@Composable
//fun RoutinesScreenPreviewMobile(){
//    RoutinesScreen()
//}
//@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_tablet")
//@Composable
//fun RoutinesScreenPreviewTablet(){
//    RoutinesScreen()
//}