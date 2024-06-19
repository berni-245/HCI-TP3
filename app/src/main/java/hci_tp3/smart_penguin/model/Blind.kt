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
        // TODO: Agregar nombres de acciones
    }
}


