package hci_tp3.smart_penguin.model

import AcMode
class Ac (
    id: String?,
    name: String,
    val room: Room?,
    val status: Status,
    val temperature: Int,
    val mode: AcMode,
    val verticalSwing: String,
    val horizontalSwing: String,
    val fanSpeed: String
) : Device(id, name, DeviceType.AC){

    companion object {
        const val TURN_ON_ACTION = "turnOn"
        const val TURN_OFF_ACTION = "turnOff"
        const val SET_TEMPERATURE_ACTION = "setTemperature"
        const val SET_MODE_ACTION = "setMode"
        const val SET_VERTICAL_SWING_ACTION = "setVerticalSwing"
        const val SET_HORIZONTAL_SWING_ACTION = "setHorizontalSwing"
        const val SET_FAN_SPEED_ACTION = "setFanSpeed"
    }
}
