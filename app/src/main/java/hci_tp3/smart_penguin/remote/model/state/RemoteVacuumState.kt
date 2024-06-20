package hci_tp3.smart_penguin.remote.model.state

import com.google.gson.annotations.SerializedName

class RemoteVacuumState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("mode")
    lateinit var mode: String

    @SerializedName("batteryLevel")
    var batteryLevel: Int = 0
}