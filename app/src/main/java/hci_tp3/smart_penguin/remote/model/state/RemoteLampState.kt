package hci_tp3.smart_penguin.remote.model.state

import com.google.gson.annotations.SerializedName

class RemoteLampState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("color")
    lateinit var color: String

    @SerializedName("brightness")
    var brightness: Int = 0
}