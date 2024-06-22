package hci_tp3.smart_penguin

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import hci_tp3.smart_penguin.ui.component.AppBar
import hci_tp3.smart_penguin.ui.component.AppBottomBar
import hci_tp3.smart_penguin.ui.navigation.AppNavGraph
import hci_tp3.smart_penguin.ui.theme.HCITP3Theme
import android.Manifest
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.isGranted

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HCITP3Theme {
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    val permisionState = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
                   if(!permisionState.status.isGranted){
                       Text(text = "Esta aplicacion necesita usar las notificaciones, por favor acepta el permiso para poder usarlas")

                   LaunchedEffect(key1 = true) {
                       permisionState.launchPermissionRequest()
                   } }
                }
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                Scaffold(
                    topBar ={ AppBar() } ,
                    bottomBar = {
                        AppBottomBar(
                            currentRoute = currentRoute
                        ) { route ->
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        AppNavGraph(navController = navController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel")
@Composable
fun MainPreview(){
    MainActivity()
}