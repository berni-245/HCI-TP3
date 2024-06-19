package hci_tp3.smart_penguin.model
import androidx.annotation.StringRes
import hci_tp3.smart_penguin.R

enum class DeviceType(@StringRes override val resourceId: Int) : LocaleEnum {
    LAMP(R.string.lamp),
    BLINDS(R.string.blind),
    AC(R.string.ac),
    VACUUM(R.string.vacuum)
}
