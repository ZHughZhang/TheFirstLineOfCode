package com.zlq.asynctasktest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {


    private val  mBinder = DownLoadBinder()



    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    /**
     * 初始化时调用
     * */
    override fun onCreate() {
        super.onCreate()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("my_service","前台Service通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent =Intent(this,MainActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,0)
        val notification = NotificationCompat.Builder(this,"my_service")
            .setContentTitle("内容标题")
            .setContentText("n内容分文字")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher))
            .setContentIntent(pi)
            .build()
        startForeground(1,notification)

    }

    /*
    * service 每次启动都会调用
    * */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    class DownLoadBinder: Binder() {
        fun  startDownLoad(){
           Log.e("MyService","startDownLoad executed")
        }
        fun getProgress():Int{
            Log.e("MyService","gteProress executed" )
            return 0

        }

    }
}