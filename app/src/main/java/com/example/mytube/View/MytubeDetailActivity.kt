package com.example.mytube.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.mytube.databinding.ActivityMytubeDetailBinding

class MytubeDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMytubeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMytubeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("video_url")

        val mediaController = MediaController(this@MytubeDetailActivity)
        binding.videoView.setVideoPath(url)
        binding.videoView.requestFocus()
        binding.videoView.start()
        mediaController.show()
    }
}