package hci_tp3.smart_penguin.model
import androidx.annotation.StringRes
import androidx.annotation.DrawableRes
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.model.state.LocaleEnum

enum class DeviceType(@StringRes override val resourceId: Int, @DrawableRes val icon: Int?): LocaleEnum {
    LAMP(R.string.lamps, R.drawable.ic_lamp),
    BLIND(R.string.blinds, R.drawable.ic_ac),
    AC(R.string.acs, R.drawable.ic_ac),
    VACUUM(R.string.vacuums, R.drawable.ic_ac)
}
