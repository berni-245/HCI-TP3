package hci_tp3.smart_penguin.remote.model

import com.google.gson.annotations.SerializedName
class RemoteAcState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("temperature")
    var temperature: Int = 18

    @SerializedName("mode")
    lateinit var mode: String

    @SerializedName("verticalSwing")
    lateinit var verticalSwing: String

    @SerializedName("horizontalSwing")
    lateinit var horizontalSwing: String

    @SerializedName("fanSpeed")
    lateinit var fanSpeed: String
}