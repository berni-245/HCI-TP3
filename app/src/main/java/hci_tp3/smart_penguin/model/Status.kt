package hci_tp3.smart_penguin.model

import LocaleEnum
import hci_tp3.smart_penguin.remote.model.RemoteStatus
import androidx.annotation.StringRes
import hci_tp3.smart_penguin.R

enum class Status(@StringRes override val resourceId: Int) : LocaleEnum {
    ON(R.string.status_on),
    OFF(R.string.status_off);

}
