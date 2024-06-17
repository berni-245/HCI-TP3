package hci_tp3.smart_penguin.remote.model

import com.google.gson.annotations.SerializedName

class RemoteError {

    @SerializedName("code")
    var code: Int = 0

    @SerializedName("description")
    lateinit var description: List<String>
}
