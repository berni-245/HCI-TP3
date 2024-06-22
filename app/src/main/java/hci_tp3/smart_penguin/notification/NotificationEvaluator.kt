package hci_tp3.smart_penguin.notification


import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.ui.res.stringResource
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.remote.DeviceRemoteDataSource
import hci_tp3.smart_penguin.remote.api.RetrofitClient
import hci_tp3.smart_penguin.remote.model.RemoteDeviceType
import hci_tp3.smart_penguin.remote.model.RemoteVacuum
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotificationEvaluator() : BroadcastReceiver() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context, intent: Intent) {
        notification(context,intent)
       /* GlobalScope.launch(Dispatchers.IO) {
            val deviceRemoteDataSource = DeviceRemoteDataSource(RetrofitClient.deviceService)
            val lowBatteryVacuum: ArrayList<String>? =
                intent?.getStringArrayListExtra("low_battery_vacuum")
            // val chargedVacuum: ArrayList<String>? = intent?.getStringArrayListExtra("charged_battery_vacuum")
            launch {
                deviceRemoteDataSource.devices.collect { list ->
                    list.forEach { device ->
                        if (device.type.id == RemoteDeviceType.VACUUM_DEVICE_TYPE_ID) {
                            val vacuum = device as RemoteVacuum
                            if (vacuum.state.batteryLevel <= 10 && !lowBatteryVacuum?.contains(vacuum.id)!!
                            ) {
                                vacuum.id?.let { lowBatteryVacuum.add(it) }
                                showNotificationLowBattery(vacuum, context, intent)
                            }else{
                                if (lowBatteryVacuum != null) {
                                    vacuum.id?.let { lowBatteryVacuum.remove(it) }
                                }
                            }

                        }
                    }
                }
            }

        }*/
    }

    private fun showNotificationLowBattery(
        vacuum: RemoteVacuum,
        context: Context?,
        intent: Intent?
    ) {

        var builder = context?.let {
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            NotificationCompat.Builder(it, "vacuumChannel")
                .setSmallIcon(R.drawable.ic_vacuum)
                .setContentTitle("Low Battery")
                .setContentText("aaaaaa robocop se queda sin bateriaaaa ayudaa")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
        }
    }

    private fun notification(
        context: Context,
        intent: Intent?
    ) {

        var builder = context.let {
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            NotificationCompat.Builder(it, "vacuumChannel")
                .setSmallIcon(R.drawable.ic_vacuum)
                .setContentTitle("Low Battery")
                .setContentText("aaaaaa robocop se queda sin bateriaaaa ayudaa")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
        }
        try {
            val notificationManager = NotificationManagerCompat.from(context)
            if(notificationManager.areNotificationsEnabled())
                notificationManager.notify(1,builder.build())
        }catch (_: SecurityException){

        }
    }


}