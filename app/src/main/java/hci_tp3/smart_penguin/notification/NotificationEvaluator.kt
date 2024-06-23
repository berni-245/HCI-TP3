package hci_tp3.smart_penguin.notification


import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import hci_tp3.smart_penguin.R
import hci_tp3.smart_penguin.remote.DeviceRemoteDataSource
import hci_tp3.smart_penguin.remote.api.RetrofitClient
import hci_tp3.smart_penguin.remote.model.RemoteDevice
import hci_tp3.smart_penguin.remote.model.RemoteDeviceType
import hci_tp3.smart_penguin.remote.model.RemoteVacuum
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotificationEvaluator() : BroadcastReceiver() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context, intent: Intent) {

        Log.d("alarma","Recibi la alarma en NotificationEvaluator")
       // notification(context,intent)
        val deviceRemoteDataSource = DeviceRemoteDataSource(RetrofitClient.deviceService)
        GlobalScope.launch(Dispatchers.IO) {
            deviceRemoteDataSource.devices.collect { list ->
                list.forEach { device ->
                   if (device.type.id == RemoteDeviceType.VACUUM_DEVICE_TYPE_ID) {
                        val vacuum = device as RemoteVacuum

                        if (vacuum.state.batteryLevel <= 10 &&  vacuum.state.status != "docked" && !lowBatteryVacuum.contains(vacuum.name)
                        ) {
                            lowBatteryVacuum.add(vacuum.name)
                            showNotificationLowBattery(vacuum, context, intent)
                        }else{
                            if(vacuum.state.batteryLevel > 10){
                                lowBatteryVacuum.remove(vacuum.name)
                            }
                        }

                    }
                }
            }

        }
    }

     fun showNotificationLowBattery(
        vacuum: RemoteDevice<*>,
        context: Context,
        intent: Intent?
    ) {
        val notificationTitle = context.resources?.getString(R.string.low_battery_vacuum_notification_title)
        val notificationTextStart = context.resources?.getString(R.string.low_battery_vacuum_notification_text_start)
        val notificationTextEnd = context.resources?.getString(R.string.low_battery_vacuum_notification_text_end)
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT or  PendingIntent.FLAG_IMMUTABLE)

         val dockIntent = Intent(context, RecieveDockRequest::class.java).apply {
             action = "dock"
             putExtra("VACUUM_ID",vacuum.id)
         }
         val dockPendingIntent : PendingIntent = PendingIntent.getBroadcast(context,0,dockIntent,
             PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, "vacuumChannel")
                .setSmallIcon(R.drawable.ic_vacuum)
                .setContentTitle(notificationTitle)
                .setContentText(notificationTextStart +" "+ vacuum.name + " " + notificationTextEnd)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_vacuum,"Send to Dock",dockPendingIntent)



        try {
            val notificationManager = NotificationManagerCompat.from(context)
            if(notificationManager.areNotificationsEnabled())
                notificationManager.notify(vacuum.id.hashCode(),builder.build())
        }catch (e: SecurityException){
            Log.d("notification","notifications not granted")
        }
    }

     fun notification(
        context: Context,
        intent: Intent?
    ) {
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val builder =  NotificationCompat.Builder(context, "vacuumChannel")
                .setSmallIcon(R.drawable.ic_lamp)
                .setContentTitle("Low Battery")
                .setContentText("robocop se queda sin pilas")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)


        try {
            val notificationManager = NotificationManagerCompat.from(context)
            if(notificationManager.areNotificationsEnabled())
                notificationManager.notify(1,builder.build())
        }catch (e: SecurityException){
            Log.d("notification","notifications not granted")
        }
    }}
