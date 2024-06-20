package hci_tp3.smart_penguin.model

class Blind (
    id: String?,
    name: String,
    val room: Room?,
    val status: String,
    val level: Int,
    val currentLevel: Int
) : Device(id, name, DeviceType.BLIND){

    companion object {
        const val OPEN_ACTION = "open"
        const val CLOSE_ACTION = "close"
        const val SET_LEVEL_ACTION = "setLevel"
    }
}


