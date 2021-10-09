package com.zlq.asynctasktest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var downLoadBinder:MyService.DownLoadBinder


    private val connection = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downLoadBinder = service as MyService.DownLoadBinder
            downLoadBinder.startDownLoad()
            downLoadBinder.getProgress()


        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start_services.setOnClickListener{
            val intent = Intent(this,MyService::class.java);
            startService(intent)
        }
        stop_service.setOnClickListener {
            val intent = Intent(this,MyService::class.java);
            stopService(intent);
        }
        bind_services.setOnClickListener{
            val intent = Intent(this,MyService::class.java)
            bindService(intent,connection, Context.BIND_AUTO_CREATE)

        }
        unbind_service.setOnClickListener {
            unbindService(connection)
        }
        start_intent_service.setOnClickListener {
            Log.e("MainActivity", "Thread is ${Thread.currentThread().name} ")
            val intent = Intent(this,MyIntentService::class.java)
            startService(intent)
        }
    }
}