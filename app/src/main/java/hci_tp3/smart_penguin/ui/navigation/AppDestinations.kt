package hci_tp3.smart_penguin.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AdfScanner
import androidx.compose.material.icons.filled.Blinds
import androidx.compose.material.icons.filled.CoffeeMaker
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Task
import androidx.compose.ui.graphics.vector.ImageVector
import hci_tp3.smart_penguin.R

enum class AppDestinations(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    DEVICES(R.string.Devices, Icons.Filled.CoffeeMaker, "devices_screen"),
    ROUTINES(R.string.Routines, Icons.Filled.Task, "routines_screen"),
    LAMP(R.string.lamps, Icons.Filled.Lightbulb, "lamp_screen"),
    AC(R.string.acs, Icons.Filled.AcUnit, "dev_ac"),
    VACUUM(R.string.vacuums, Icons.Filled.AdfScanner, "dev_vac"),
    BLIND(R.string.blinds, Icons.Filled.Blinds,"dev_bln")
}