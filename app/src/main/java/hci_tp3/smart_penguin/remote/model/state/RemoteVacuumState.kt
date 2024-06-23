package hci_tp3.smart_penguin.remote.model.state

import com.google.gson.annotations.SerializedName
import hci_tp3.smart_penguin.remote.model.RemoteRoom

class RemoteVacuumState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("mode")
    lateinit var mode: String

    @SerializedName("batteryLevel")
    var batteryLevel: Int = 0

    @SerializedName("location")
    var location: RemoteRoom? = null
}