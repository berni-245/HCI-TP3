package hci_tp3.smart_penguin.model

import LocaleEnum
import androidx.annotation.StringRes
import hci_tp3.smart_penguin.R

enum class VacuumMode(@StringRes override val resourceId: Int, override val apiString: String) : LocaleEnum {
    VACUUM(R.string.vacuum_mode_vacuum, "vacuum"),
    MOP(R.string.vacuum_mode_mop, "mop")
}