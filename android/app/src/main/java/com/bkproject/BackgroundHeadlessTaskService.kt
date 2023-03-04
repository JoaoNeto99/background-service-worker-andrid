package com.bkproject

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.facebook.react.HeadlessJsTaskService
import com.facebook.react.bridge.Arguments
import com.facebook.react.jstasks.HeadlessJsTaskConfig


class BackgroundHeadlessTaskService : HeadlessJsTaskService() {

    override fun getTaskConfig(intent: Intent?): HeadlessJsTaskConfig {
        Log.d("BGTESTE", "getTaskConfig")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
            val notification: Notification = NotificationCompat.Builder(applicationContext, "demo")
                    .setContentTitle("Headless Work")
                    .setTicker("runn")
                    .setOngoing(true)
                    .build()
            startForeground(1, notification)
        }

        val extras = intent!!.extras
        Log.d("BGTESTE", "getTaskConfig HeadlessJsTaskConfig")
        return HeadlessJsTaskConfig(
                "BackgroundHeadlessTaskService",
                if (extras != null) Arguments.fromBundle(extras) else Arguments.createMap(),
                0,
                true
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val description = "test channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("demo", "test", importance)
        channel.description = description
        val notificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}



