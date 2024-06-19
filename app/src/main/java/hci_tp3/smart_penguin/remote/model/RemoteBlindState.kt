package hci_tp3.smart_penguin.remote.model

import com.google.gson.annotations.SerializedName

class RemoteBlindState {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("level")
    var level: Int = 0

    @SerializedName("currentLevel")
    var currentLevel: Int = 0
}
