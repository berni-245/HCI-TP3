package hci_tp3.smart_penguin.notification


import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import hci_tp3.smart_penguin.R
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
                                    showNotification(vacuum,context,intent)
                            }

                        }
                    }
                }
            }

        }
    }

    private fun showNotification(vacuum: RemoteVacuum, context: Context?, intent: Intent?) {

            var builder = context?.let {
                val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
                NotificationCompat.Builder(it, "vacuumChannel")
                    .setSmallIcon(R.drawable.ic_vacuum)
                    .setContentTitle(vacuum.name)
                    .setContentText("aaaaaa robocop se queda sin bateriaaaa ayudaa")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
            }
        }



}