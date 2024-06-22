package hci_tp3.smart_penguin.notification


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hci_tp3.smart_penguin.remote.DeviceRemoteDataSource
import hci_tp3.smart_penguin.remote.model.RemoteDeviceType
import hci_tp3.smart_penguin.remote.model.RemoteVacuum
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotificationEvaluator(deviceRemoteDataSource: DeviceRemoteDataSource) : BroadcastReceiver() {
    var devices = deviceRemoteDataSource.devices
    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context?, intent: Intent?) {
        GlobalScope.launch(Dispatchers.IO) {
            launch {
                devices.collect { list ->
                    list.forEach { device ->
                        if(device.type.id == RemoteDeviceType.VACUUM_DEVICE_TYPE_ID){
                            val vacuum =  device as RemoteVacuum
                            if(vacuum.state.batteryLevel <= 10){

                            }

                        }
                    }
                }
            }

        }
    }


}