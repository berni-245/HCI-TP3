package hci_tp3.smart_penguin.remote.model

import com.google.gson.annotations.SerializedName

class RemoteRoomMeta {
    @SerializedName("size")
    lateinit var size: String

    @SerializedName("color")
    lateinit var color: String
}