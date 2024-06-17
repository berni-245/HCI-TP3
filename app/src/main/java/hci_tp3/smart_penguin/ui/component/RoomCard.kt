package hci_tp3.smart_penguin.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hci_tp3.smart_penguin.model.Room

@Composable
fun RoomCard(
    room: Room
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor(room.color))
        ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = room.name,
                    fontSize = 16.sp
                )
                Text(
                    text = room.size,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun RoomCardPreview() {
    RoomCard(
        Room(
            name = "Kitchen",
            size = "6m2",
            color = "#aade8a"
        )
    )
}