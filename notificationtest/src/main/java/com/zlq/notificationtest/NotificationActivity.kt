package com.zlq.notificationtest

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationManagerCompat

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        //显示取消通知
        //val manage = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val managCompat = NotificationManagerCompat.from(this)//同上
        managCompat.cancel(1)
        //manage.cancel(1)
    }
}