package hci_tp3.smart_penguin.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hci_tp3.smart_penguin.ui.navigation.AppDestinations
import androidx.compose.material3.MaterialTheme

@Composable
fun AppNavigationRail(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    val items = listOf(
        AppDestinations.DEVICES,
        AppDestinations.ROUTINES
    )

    NavigationRail(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight().background(color = MaterialTheme.colorScheme.primaryContainer)

        ) {
            items.forEach { item ->
                NavigationRailItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = stringResource(item.title)) },
                    label = { Text(text = stringResource(item.title)) },
                    alwaysShowLabel = true,
                    selected = currentRoute == item.route,
                    onClick = { onNavigateToRoute(item.route) }
                )
            }
        }
    }
}