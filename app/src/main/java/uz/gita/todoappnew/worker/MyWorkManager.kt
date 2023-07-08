package uz.gita.todoappnew.worker

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import uz.gita.todoappnew.data.model.TaskData
import uz.gita.todoappnew.util.formatCalendar
import uz.gita.todoappnew.util.getCalendar
import java.util.Calendar
import java.util.concurrent.TimeUnit

fun Context.createWorkRequest(taskData: TaskData) {

    val calendar = getCalendar(taskData.timer)

    val initialDelay = calendar.timeInMillis - System.currentTimeMillis()

    if (initialDelay > 0) {
        val data = Data.Builder().putLong(TASK_ID, taskData.id).build()
        val workRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(this)
            .enqueueUniqueWork(taskData.getWorkerId(), ExistingWorkPolicy.REPLACE, workRequest)
    } else {
        cancelWorkRequest(taskData)
    }
}

fun Context.cancelWorkRequest(taskData: TaskData){
    WorkManager.getInstance(this).cancelUniqueWork(taskData.getWorkerId())
}