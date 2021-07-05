package com.zlq.playaudiotest

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mediaPlay = MediaPlayer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMediaPlayer()
        play.setOnClickListener {
            if (!mediaPlay.isPlaying) {
                mediaPlay.start()
            }
        }
        pause.setOnClickListener {
            if (mediaPlay.isPlaying) {
                mediaPlay.pause()
            }
        }
        stop.setOnClickListener {
            if (mediaPlay.isPlaying) {
                mediaPlay.reset()
                initMediaPlayer()
            }
        }
    }

    private fun initMediaPlayer() {
        val assetManager = assets
        val fd = assetManager.openFd("music.mp3")
        mediaPlay.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlay.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlay.stop()
        mediaPlay.release()
    }
}