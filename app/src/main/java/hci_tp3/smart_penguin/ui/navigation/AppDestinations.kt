package hci_tp3.smart_penguin.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CoffeeMaker
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Task
import androidx.compose.ui.graphics.vector.ImageVector
import hci_tp3.smart_penguin.R

enum class AppDestinations(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    DEVICES(R.string.Devices, Icons.Filled.CoffeeMaker, "devices_screen"),
    ROUTINES(R.string.Routines, Icons.Filled.Task, "routines_screen")
}