package com.bkproject

import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
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

    override fun getName(): String {
        return "BackgroundWorkManager"
    }
}