package hci_tp3.smart_penguin.model

import hci_tp3.smart_penguin.remote.model.RemoteRoom
import hci_tp3.smart_penguin.remote.model.RemoteRoomMeta

class Room(
    var id: String? = null,
    var name: String,
    var size: String,
    var color: String
) {

    fun asRemoteModel(): RemoteRoom {
        val meta = RemoteRoomMeta()
        meta.size = size
        meta.color = color

        val model = RemoteRoom()
        model.id = id
        model.name = name
        model.meta = meta

        return model
    }
}