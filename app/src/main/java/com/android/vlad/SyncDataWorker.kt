package com.android.vlad

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SyncDataWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    
    override suspend fun doWork(): Result {

        return Result.success()
    }
}