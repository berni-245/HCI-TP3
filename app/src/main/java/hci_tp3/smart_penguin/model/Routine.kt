package hci_tp3.smart_penguin.model

import hci_tp3.smart_penguin.remote.model.RemoteRoutine

class Routine(
    var id: String? = null,
    var name: String,
    var actions: List<Action>,
    var meta: Any? = null // TODO: Add a metadata for description of the routine
) {
    fun asRemoteModel() : RemoteRoutine {
        val remoteModel = RemoteRoutine()
        remoteModel.id = id
        remoteModel.name = name
        remoteModel.actions = actions.map { it.asRemoteModel() }
        remoteModel.meta = meta
        return remoteModel
    }
}