package com.falin.valentin.foodapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.falin.valentin.foodapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

/**
 *  [FirebaseMessagingService]-subclass for work with notifications.
 *
 */
class AppFirebaseMessaging : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(message)
        }

        Timber.i("FirebaseMessaging222 ${message.notification?.title} ${message.notification?.body}")

        val builder =
            NotificationCompat.Builder(this, message.messageId.toString())
                .setSmallIcon(R.drawable.ic_account_on)
                .setContentTitle(message.notification?.title)
                .setContentText(message.notification?.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(this).notify(1, builder.build())
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createNotificationChannel(message: RemoteMessage) {
        val channel = NotificationChannel(
            message.messageId,
            message.notification?.title,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = message.notification?.body
        }
        val notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}