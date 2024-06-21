package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Room
import com.google.gson.annotations.SerializedName

class RemoteRoom {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("meta")
    var meta: Any? = null

    fun asModel() : Room {
        return Room(
            id = id,
            name = name
        )
    }
}