package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Device
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import hci_tp3.smart_penguin.model.DeviceForRoutine
import hci_tp3.smart_penguin.model.DeviceType

class RemoteDeviceForRoutine {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("type")
    @Expose(serialize = false)
    lateinit var type: RemoteDeviceType

    @SerializedName("meta")
    var meta: Any? = null


    fun asModel(): DeviceForRoutine {
        var type = type.name
        if(type == "blinds")
            type = "blind"
        return DeviceForRoutine(id, name, DeviceType.valueOf(type.uppercase()))
    }
}