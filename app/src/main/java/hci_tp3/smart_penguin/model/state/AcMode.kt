package hci_tp3.smart_penguin.model.state

import androidx.annotation.StringRes
import hci_tp3.smart_penguin.R

enum class AcMode(@StringRes override val resourceId: Int, override val apiString: String) :
    LocaleEnum {
    COOL(R.string.ac_mode_cool, "cool"),
    HEAT(R.string.ac_mode_heat, "heat"),
    FAN(R.string.ac_mode_fan, "fan");
}
