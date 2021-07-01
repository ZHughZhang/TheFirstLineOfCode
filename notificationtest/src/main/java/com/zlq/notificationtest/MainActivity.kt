package com.zlq.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel =
//                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
//            manager.createNotificationChannel(channel)

            val channel2 =
                NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel2)

        }
        sendNotice.setOnClickListener {

            val intent = Intent(this,NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this,0,intent,0)
            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("This is content title")
                .setStyle(NotificationCompat.BigTextStyle().bigText("""Learn how to build notifications, send and sync data,
                        and use voice actions.Get the official Android IDE and developer tools to
                        build apps for Android."""))//设置文字样子，这里设置的是是文字全部显示不使用省略号
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher)))
                .setSmallIcon(android.R.drawable.ic_menu_send)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher))
                .setContentIntent(pi)
//                .setAutoCancel(true)//点击之后自动取消掉通知消息
                .build()
            manager.notify(1,notification)
        }




    }
}