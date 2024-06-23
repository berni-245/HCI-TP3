package hci_tp3.smart_penguin.notification

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hci_tp3.smart_penguin.model.Vacuum
import hci_tp3.smart_penguin.remote.DeviceRemoteDataSource
import hci_tp3.smart_penguin.remote.api.RetrofitClient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecieveDockRequest : BroadcastReceiver() {
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("UnsafeIntentLaunch")
    override  fun onReceive(context: Context, intent: Intent) {
        val vacuumId = intent.getStringExtra("VACUUM_ID")
         val deviceRemoteDataSource  = DeviceRemoteDataSource(RetrofitClient.deviceService)
       // val notificationTitle = context.resources?.getString(R.string.dock_vacuum_from_notification_title)

        GlobalScope.launch(Dispatchers.IO) {
            if (vacuumId != null ) {
                val arr = emptyArray<String>()
                deviceRemoteDataSource.executeDeviceAction(vacuumId, Vacuum.DOCK_ACTION,arr)

/*
                val pendingIntent: PendingIntent =
                    PendingIntent.getActivity(context, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or  PendingIntent.FLAG_IMMUTABLE)

                val builder = NotificationCompat.Builder(context, "vacuumChannel")
                    .setSmallIcon(R.drawable.ic_vacuum)
                    .setContentTitle(notificationTitle)
                    .setContentText(" ")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)



                try {
                    val notificationManager = NotificationManagerCompat.from(context)
                    if(notificationManager.areNotificationsEnabled())
                        notificationManager.notify(vacuumId.hashCode(),builder.build())
                }catch (e: SecurityException){
                    Log.d("notification","notifications not granted")
                }*/
            }
        }
    }
}