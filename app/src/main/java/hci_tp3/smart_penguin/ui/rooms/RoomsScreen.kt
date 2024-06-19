package hci_tp3.smart_penguin.ui.rooms

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.Room
import hci_tp3.smart_penguin.ui.component.ActionButton
import hci_tp3.smart_penguin.ui.component.RoomCard
import hci_tp3.smart_penguin.ui.getViewModelFactory
import kotlin.random.Random

@Composable
fun RoomsScreen(
    viewModel: RoomsViewModel = viewModel(factory = getViewModelFactory()),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ActionButton(
            text = R.string.get_rooms,
            onClick = {
                viewModel.getRooms()
            })
        ActionButton(
            text = R.string.get_current_room,
            enabled = uiState.canGetCurrent,
            onClick = {
                val currentRoom = uiState.currentRoom!!
                viewModel.getRoom(currentRoom.id!!)
            })
        ActionButton(
            text = R.string.add_room,
            onClick = {
                val random1 = Random.nextInt(0, 100)
                val random2 = Random.nextInt(0, 100)
                val color = String.format("#%06x", generateRandomColor())
                val room = Room(name = "Room $random1", size = "${random2}m2", color = color)
//                viewModel.addRoom(room)
            })
        ActionButton(
            text = R.string.modify_room,
            enabled = uiState.canModify,
            onClick = {
                val random2 = Random.nextInt(0, 100)
                val currentRoom = uiState.currentRoom!!
                val room = Room(
                    id = currentRoom.id,
                    name = currentRoom.name,
                    size = "${random2}m2",
                    color = currentRoom.color
                )
//                viewModel.modifyRoom(room)
            })
        ActionButton(
            text = R.string.delete_room,
            enabled = uiState.canDelete,
            onClick = {
                val currentSport = uiState.currentRoom!!
//                viewModel.deleteRoom(currentSport.id!!)
            })
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = uiState.error?.message ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                fontSize = 18.sp
            )
            val currentRoomData = uiState.currentRoom?.let {
                "(${it.id}) ${it.name} - ${it.size}"
            }
            Text(
                text = stringResource(R.string.current_room, currentRoomData ?: ""),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                fontSize = 18.sp
            )
            Text(
                text = stringResource(R.string.total_rooms, uiState.rooms.size),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                fontSize = 18.sp
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 196.dp),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                items(
                    count = uiState.rooms.size,
                    key = { index ->
                        uiState.rooms[index].id!!
                    }
                ) { index ->
                    RoomCard(uiState.rooms[index])
                }
            }
        }
    }
}

var random = Random(System.currentTimeMillis())

fun generateRandomColor(): Int {
    val baseColor: Int = Color.WHITE
    val baseRed: Int = Color.red(baseColor)
    val baseGreen: Int = Color.green(baseColor)
    val baseBlue: Int = Color.blue(baseColor)
    val red: Int = (baseRed + random.nextInt(256)) / 2
    val green: Int = (baseGreen + random.nextInt(256)) / 2
    val blue: Int = (baseBlue + random.nextInt(256)) / 2
    return Color.rgb(red, green, blue)
}