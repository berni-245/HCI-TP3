package hci_tp3.smart_penguin.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.ui.theme.HCITP3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier) {

    TopAppBar(
        navigationIcon = {},
        title = {

                Image(
                    painter =   painterResource(id = R.drawable.penguin),
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxSize().padding(top = 10.dp, bottom = 10.dp)

                )

        },
        colors = topAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AppBArPreview() {
    HCITP3Theme {
        AppBar()
    }

}