package hci_tp3.smart_penguin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import hci_tp3.smart_penguin.remote.model.RemoteDeviceType

class DeviceForRoutine (
    val id: String? = null,
    val name: String,
    val type: DeviceType
){
}