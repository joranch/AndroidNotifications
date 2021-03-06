/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.android.eggtimernotifications.MainActivity
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

// TODO: Step 1.1 extension function to send messages (GIVEN)
/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        applicationContext, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        REQUEST_CODE,
        snoozeIntent,
        PendingIntent.FLAG_UPDATE_CURRENT)

    val eggImage = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.cooked_egg)
    val bigPictureStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.resources.getString(R.string.egg_notification_channel_id))

    builder.setSmallIcon(R.drawable.egg_icon)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPictureStyle)
        .setLargeIcon(eggImage)
        .addAction(R.drawable.egg_icon, applicationContext.getString(R.string.snooze), snoozePendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

        // TODO: Step 2.5 set priority

    notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}
