package hci_tp3.smart_penguin.remote.model

import com.google.gson.annotations.SerializedName
import hci_tp3.smart_penguin.model.Routine

class RemoteRoutine {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("actions")
    lateinit var actions: List<RemoteAction>

    @SerializedName("meta")
    var meta: RemoteRoutineMeta? = null

    fun asModel() : Routine {
        return Routine(
            id = id,
            name = name,
            actions = actions.map { it.asModel() },
            desc = meta?.desc!!
        )
    }
}
