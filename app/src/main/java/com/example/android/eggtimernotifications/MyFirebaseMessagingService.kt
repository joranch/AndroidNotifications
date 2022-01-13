package com.example.android.eggtimernotifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FCM service", "Received message from: ${remoteMessage.from}")

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("FCM service", "Refreshed token: $token")
    }
}