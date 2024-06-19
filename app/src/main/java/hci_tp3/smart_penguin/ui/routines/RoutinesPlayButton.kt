package hci_tp3.smart_penguin.ui.routines


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoutinePlayButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonColors(
            contentColor = Color.White,
            disabledContentColor = Color.LightGray ,
            containerColor = Color.Green,
            disabledContainerColor = Color.DarkGray
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Play",
            tint = Color.White,
            modifier = Modifier.size(15.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel")
@Composable
fun RoutinePlayButtonPreview(){
    RoutinePlayButton {

    }
}

