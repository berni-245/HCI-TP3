import androidx.annotation.StringRes
import androidx.compose.ui.text.toUpperCase
import hci_tp3.smart_penguin.R
import java.util.Locale

enum class AcMode(@StringRes override val resourceId: Int, override val apiString: String) : LocaleEnum {
    COOL(R.string.ac_mode_cool, "cool"),
    HEAT(R.string.ac_mode_heat, "heat"),
    FAN(R.string.ac_mode_fan, "fan");
}
