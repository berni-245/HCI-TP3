package hci_tp3.smart_penguin.model

import LocaleEnum
import androidx.annotation.StringRes
import hci_tp3.smart_penguin.R

enum class VacuumStatus(@StringRes override val resourceId: Int, override val apiString: String) : LocaleEnum {
    DOCKED(R.string.vacuum_status_docked, "docked"),
    INACTIVE(R.string.vacuum_status_inactive, "inactive"),
    ACTIVE(R.string.vacuum_status_active, "active")
    ;
}