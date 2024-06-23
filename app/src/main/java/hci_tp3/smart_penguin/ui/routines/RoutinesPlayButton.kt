package hci_tp3.smart_penguin.ui.routines


import androidx.compose.foundation.layout.RowScope
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
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.ui.getViewModelFactory



@Composable
fun RoutinePlayButton(
    id : String,
    viewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory()),
) {

    Button(
        onClick = {execRoutine(id,viewModel)},
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


private fun execRoutine(id : String, viewModel: RoutinesViewModel){
    viewModel.executeRoutine(id)
}

/*
@Preview(showBackground = true)
@Composable
fun RoutinePlayButtonPreview(){
    RoutinePlayButton {

    }
}

*/
