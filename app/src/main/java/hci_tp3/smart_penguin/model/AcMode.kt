import androidx.annotation.StringRes
import hci_tp3.smart_penguin.R

enum class AcMode(@StringRes override val resourceId: Int) : LocaleEnum {
    COOL(R.string.ac_mode_cool),
    HEAT(R.string.ac_mode_heat),
    FAN(R.string.ac_mode_fan)
}
