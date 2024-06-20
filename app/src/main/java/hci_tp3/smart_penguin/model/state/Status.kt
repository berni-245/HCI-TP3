package hci_tp3.smart_penguin.model.state

import androidx.annotation.StringRes
import hci_tp3.smart_penguin.R

enum class Status(@StringRes override val resourceId: Int, override val apiString: String) :
    LocaleEnum {
    ON(R.string.status_on, "on"),
    OFF(R.string.status_off, "off");
}
