package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FCM service", "Received message from: ${remoteMessage.from}")

        remoteMessage?.data?.let {
            Log.d("FCM service", "Message data payload: " + remoteMessage.data)
        }

         remoteMessage.notification?.let {
             sendNotification(it.body!!)
         }
    }

    private fun sendNotification(message: String) {
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java)
            as NotificationManager
        notificationManager.sendNotification(message, applicationContext)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("FCM service", "Refreshed token: $token")
    }
}