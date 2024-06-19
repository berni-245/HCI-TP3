package hci_tp3.smart_penguin.model

import hci_tp3.smart_penguin.remote.model.RemoteRoutine

class Routine(
    var id: String? = null,
    var name: String,
    var actions: List<Action>,
    var meta: Any? = null // TODO: Add a metadata for description of the routine
) {
}