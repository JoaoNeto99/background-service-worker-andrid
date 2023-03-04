package com.bkproject

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


//implementation 'androidx.work:work-runtime:workmanagerversion'

class BackgroundWorker(val context: Context, val workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.w("BGTESTE", "Worker do work")

        val service = Intent(this.context, BackgroundHeadlessTaskService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         //   createChannel()
            this.context.startForegroundService(service)
        } else {
            this.context.startService(service)
        }
        return Result.success()
    }
}