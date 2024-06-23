package hci_tp3.smart_penguin.ui.component

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.notification.notifictionsEnabled
import hci_tp3.smart_penguin.ui.navigation.AppDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navBackStackEntry = navController.currentDestination
    val currentRoute = navBackStackEntry?.route
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var expanded by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        navigationIcon = {
            if (currentRoute != AppDestinations.DEVICES.route && currentRoute != AppDestinations.ROUTINES.route) {
                IconButton(onClick = {
                    onBackPressedDispatcher?.onBackPressed()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        title = {
            Image(
                painter = painterResource(id = R.drawable.penguin),
                contentDescription = "Logo",
                modifier = Modifier.height(50.dp),
                alignment = Alignment.Center
            )
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                if(!notifictionsEnabled){
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        notifictionsEnabled = true
                    },
                    text = { Text(stringResource(id = R.string.noti_enable)) }
                )} else {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        notifictionsEnabled = false
                    },
                    text = { Text(stringResource(id = R.string.noti_disable)) }
                )}
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = modifier
    )
}