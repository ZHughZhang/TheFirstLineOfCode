package com.zlq.asynctasktest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
    }
}