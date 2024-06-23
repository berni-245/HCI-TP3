package hci_tp3.smart_penguin.ui.routines


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.ui.getViewModelFactory



@Composable
fun RoutinePlayButton(
    id : String,
    viewModel: RoutinesViewModel = viewModel(factory = getViewModelFactory()),
) {

    var enabled by remember { mutableStateOf(true) }
    //var buttonColor by remember { mutableStateOf( Color(0xff304ffe)) }

    Button(
        enabled = enabled,
        onClick = {
            execRoutine(id,viewModel)
            enabled = false
                  },
        colors = ButtonColors(

            contentColor = Color.White,
            disabledContentColor = Color.Green ,
            containerColor = Color(0xff304ffe),
            disabledContainerColor = Color.DarkGray
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        if( enabled ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(15.dp)
            )
        }else{
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(15.dp))
        }
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
