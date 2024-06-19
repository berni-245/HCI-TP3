package hci_tp3.smart_penguin.model

class Routine(
    var id: String? = null,
    var name: String,
    var actions: List<Action>,
    var meta: Any? = null // TODO: Add a metadata for description of the routine
) {
}