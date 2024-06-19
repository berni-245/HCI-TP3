package hci_tp3.smart_penguin.remote.api

import hci_tp3.smart_penguin.remote.model.RemoteDevice
import hci_tp3.smart_penguin.remote.model.RemoteDeviceType
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import hci_tp3.smart_penguin.remote.model.RemoteLamp
import hci_tp3.smart_penguin.remote.model.RemoteAc
import hci_tp3.smart_penguin.remote.model.RemoteBlind
import hci_tp3.smart_penguin.remote.model.RemoteVacuum
import java.lang.reflect.Type

class DeviceTypeAdapter : JsonDeserializer<RemoteDevice<*>?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): RemoteDevice<*>? {
        val gson = Gson()
        val jsonDeviceObject = json.asJsonObject
        val jsonDeviceTypeObject = jsonDeviceObject["type"].asJsonObject
        val deviceTypeId = jsonDeviceTypeObject["id"].asString
        return when (deviceTypeId) {
            RemoteDeviceType.LAMP_DEVICE_TYPE_ID -> {
                gson.fromJson(jsonDeviceObject, object : TypeToken<RemoteLamp?>() {}.type)
            }
            RemoteDeviceType.AC_DEVICE_TYPE_ID -> {
                gson.fromJson(jsonDeviceObject, object : TypeToken<RemoteAc?>() {}.type)
            }
            RemoteDeviceType.VACUUM_DEVICE_TYPE_ID -> {
                gson.fromJson(jsonDeviceObject, object : TypeToken<RemoteVacuum?>() {}.type)
            }
            RemoteDeviceType.BLINDS_DEVICE_TYPE_ID -> {
                gson.fromJson(jsonDeviceObject, object : TypeToken<RemoteBlind?>() {}.type)
            }
            else -> null
        }

    }
}
