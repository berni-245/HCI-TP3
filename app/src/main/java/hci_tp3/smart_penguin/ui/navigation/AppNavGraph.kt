package hci_tp3.smart_penguin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hci_tp3.smart_penguin.ui.devices.DevicesScreen
import hci_tp3.smart_penguin.ui.rooms.RoomsScreen
import hci_tp3.smart_penguin.ui.routines.RoutinesScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.ROOMS.route
    ) {
        composable(AppDestinations.ROOMS.route) {
            RoomsScreen()
        }
        composable(AppDestinations.DEVICES.route) {
            DevicesScreen()
        }
        composable(AppDestinations.ROUTINES.route) {
            RoutinesScreen()
        }
    }
}