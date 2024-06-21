package hci_tp3.smart_penguin.remote.model

import com.google.gson.annotations.SerializedName
import hci_tp3.smart_penguin.model.Action

class RemoteAction {
    @SerializedName("device")
    lateinit var deviceForRoutine: RemoteDeviceForRoutine

    @SerializedName("actionName")
    lateinit var actionName: String

    @SerializedName("params")
    lateinit var params: List<String>

    @SerializedName("meta")
    var meta: Any? = null

    fun asModel(): Action {
        return Action(
            deviceForRoutine.asModel(),
            actionName,
            params,
            meta
        )
    }
}
