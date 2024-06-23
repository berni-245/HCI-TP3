package hci_tp3.smart_penguin

import androidx.compose.ui.graphics.Color

fun hexToColor(hex: String): Color {
    // Remove the leading '#' if present
    val colorString = hex.removePrefix("#")
    val colorInt = android.graphics.Color.parseColor("#$colorString")
    return Color(colorInt)
}
