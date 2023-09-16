package com.bkproject

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.facebook.react.HeadlessJsTaskService
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import java.util.concurrent.TimeUnit


class BackgroundModule(val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private var workRequest: PeriodicWorkRequest = PeriodicWorkRequest.Builder(BackgroundWorker::class.java, 15, TimeUnit.MINUTES).build()

    @ReactMethod
    fun startBackgroundWork() {
        Log.d("BGTESTE", "startBackgroundWork")
        WorkManager.getInstance(reactContext).enqueueUniquePeriodicWork("testWork", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }

    @ReactMethod
    fun stopBackgroundWork() {
        Log.d("BGTESTE", "stopBackgroundWork")
        WorkManager.getInstance(reactContext).cancelUniqueWork("testWork")
    }

    @ReactMethod
    fun isRunning(promise: Promise){
        val activityManager: ActivityManager = reactContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val services: List<ActivityManager.RunningServiceInfo> = activityManager.getRunningServices(Integer.MAX_VALUE)

        for (runningServiceInfo in services) {
            if(runningServiceInfo.service.className == BackgroundWorker::class.java.name){
                promise.resolve(true)
            }
        }
        promise.resolve(false)
    }

    override fun getName(): String {
        return "BackgroundWorkManager"
    }
    
}