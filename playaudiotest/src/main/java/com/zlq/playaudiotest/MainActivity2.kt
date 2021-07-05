package com.zlq.playaudiotest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.hh}")
        videoView.setVideoURI(uri)

        play.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }
        pause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }
        replay.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}