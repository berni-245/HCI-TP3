package hci_tp3.smart_penguin.model
import LocaleEnum
import androidx.annotation.StringRes
import hci_tp3.smart_penguin.R

enum class DeviceType(@StringRes override val resourceId: Int) : LocaleEnum {
    LAMP(R.string.lamps),
    BLINDS(R.string.blinds),
    AC(R.string.acs),
    VACUUM(R.string.vacuums)
}
