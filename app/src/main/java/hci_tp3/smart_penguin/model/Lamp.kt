package hci_tp3.smart_penguin.model

class Lamp(
    id: String?,
    name: String,
    val room: Room?,
    val status: Status,
    val color: String,
    val brightness: Int
) : Device(id, name, DeviceType.LAMP) {

    companion object {
        const val TURN_ON_ACTION = "turnOn"
        const val TURN_OFF_ACTION = "turnOff"
    }
}