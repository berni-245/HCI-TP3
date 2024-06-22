package hci_tp3.smart_penguin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hci_tp3.smart_penguin.ui.devices.DevicesScreen
import hci_tp3.smart_penguin.ui.devices.blind.BlindScreen
import hci_tp3.smart_penguin.ui.devices.lamp.LampScreen
import hci_tp3.smart_penguin.ui.routines.RoutinesScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.DEVICES.route
    ) {
        composable(AppDestinations.DEVICES.route) {
            DevicesScreen()
        }
        composable(AppDestinations.ROUTINES.route) {
            RoutinesScreen()
        }
        composable(AppDestinations.LAMP.route){
            LampScreen(onNavigateDestination = {route -> navController.navigate(route)})
        }
        composable(AppDestinations.AC.route){
            //TODO: Add ACScreen()
        }
        composable(AppDestinations.VACUUM.route){
            //TODO: Add VacuumScreen()
        }
        composable(AppDestinations.BLIND.route) {
            BlindScreen()
        }
    }
}