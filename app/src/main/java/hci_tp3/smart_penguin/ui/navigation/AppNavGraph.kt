package hci_tp3.smart_penguin.ui.navigation

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import hci_tp3.smart_penguin.ui.devices.DevicesScreen
import hci_tp3.smart_penguin.ui.devices.blind.BlindScreen
import hci_tp3.smart_penguin.ui.devices.lamp.LampScreen
import hci_tp3.smart_penguin.ui.devices.DevicesScreenTablet
import hci_tp3.smart_penguin.ui.devices.ac.AcScreen
import hci_tp3.smart_penguin.ui.devices.vacuum.VacuumScreen
import hci_tp3.smart_penguin.ui.routines.RoutinesScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.DEVICES.route,
    ) {
        composable(AppDestinations.DEVICES.route) {
            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
                DevicesScreen(onNavigateDestination = {route -> navController.navigate(route)})
            } else {
                DevicesScreenTablet(onNavigateDestination = {route -> navController.navigate(route)})
            }
        }
        composable(AppDestinations.ROUTINES.route) {
            RoutinesScreen()
        }
        composable(AppDestinations.LAMP.route){
            LampScreen()
        }
        composable(AppDestinations.AC.route){
            AcScreen()
        }
        composable(AppDestinations.VACUUM.route){
            VacuumScreen()
        }
        composable(AppDestinations.BLIND.route) {
            BlindScreen()
        }
    }
}