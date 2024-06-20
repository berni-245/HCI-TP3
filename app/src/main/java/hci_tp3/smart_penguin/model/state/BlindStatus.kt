package hci_tp3.smart_penguin.model.state

import androidx.annotation.StringRes
import hci_tp3.smart_penguin.R

enum class BlindStatus(@StringRes override val resourceId: Int, override val apiString: String) :
    LocaleEnum {
    OPENING(R.string.blind_status_opening, "opening"),
    OPENED(R.string.blind_status_opened, "opened"),
    CLOSING(R.string.blind_status_closing, "closing"),
    CLOSED(R.string.blind_status_closed, "closed")
    ;
}