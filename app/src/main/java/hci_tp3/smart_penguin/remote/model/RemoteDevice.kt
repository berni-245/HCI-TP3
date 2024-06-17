package hci_tp3.smart_penguin.remote.model

import hci_tp3.smart_penguin.model.Device
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

abstract class RemoteDevice<T> where T : Any {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("type")
    @Expose(serialize = false)
    lateinit var type: RemoteDeviceType

    @SerializedName("room")
    var room: RemoteRoom? = null

    @Expose(serialize = false)
    lateinit var state: T
        private set

    @SerializedName("meta")
    var meta: Any? = null

    fun setState(state: T) {
        this.state = state
    }

    abstract fun asModel(): Device
}