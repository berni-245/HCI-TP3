package hci_tp3.smart_penguin.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.ui.theme.HCITP3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier) {

    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
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