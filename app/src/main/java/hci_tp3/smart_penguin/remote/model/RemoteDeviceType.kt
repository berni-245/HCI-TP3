package hci_tp3.smart_penguin.remote.model

import com.google.gson.annotations.SerializedName

class RemoteDeviceType {
    @SerializedName("id")
    lateinit var id: String

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("powerUsage")
    var powerUsage: Int? = null

    companion object {
        const val AC_DEVICE_TYPE_ID = "li6cbv5sdlatti0j"
        const val VACUUM_DEVICE_TYPE_ID = "ofglvd9gqx8yfl3l"
        const val LAMP_DEVICE_TYPE_ID = "go46xmbqeomjrsjr"
        const val BLINDS_DEVICE_TYPE_ID = "eu0v2xgprrhhg41g"
    }
}