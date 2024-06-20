package hci_tp3.smart_penguin.model

class Vacuum (
    id: String?,
    name: String,
    val room: Room?,
    val status: VacuumStatus,
    val mode: VacuumMode,
    val batteryLevel: Int
) : Device(id, name, DeviceType.VACUUM){

    companion object {
        const val START_ACTION = "start"
        const val PAUSE_ACTION = "pause"
        const val DOCK_ACTION = "dock"
        const val SET_MODE_ACTION = "setMode"
        const val SET_LOCATION_ACTION = "setLocation"
    }
}