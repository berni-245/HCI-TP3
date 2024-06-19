package hci_tp3.smart_penguin.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hci_tp3.smart_penguin.ui.navigation.AppDestinations

@Composable
fun AppBottomBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    val items = listOf(
        AppDestinations.DEVICES,
        AppDestinations.ROUTINES
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = stringResource(item.title)) },
                label = { Text(text = stringResource(item.title)) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = { onNavigateToRoute(item.route) }
            )
        }
    }
}

@Preview( showBackground = true)
@Composable
fun Prev(){
    AppBottomBar(currentRoute = "routines") {
        
    }
}