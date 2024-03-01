package com.ahuynh.harrypotterapp.ui.main

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ahuynh.harrypotterapp.R
import com.ahuynh.harrypotterapp.databinding.ActivityMainBinding
import com.ahuynh.harrypotterapp.ui.detail.DetailActivity.Companion.startActivityWithTransition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var isPaused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPager()
        setUpMedia()
    }

    private fun setUpViewPager() {
        val houseAdapter = MainAdapter { view, type ->
            startActivityWithTransition(this@MainActivity, view, type)
        }
        binding.viewPgHouse.adapter = houseAdapter

    }

    override fun onResume() {
        super.onResume()
        if (isPaused) {
            mediaPlayer.start()
            isPaused = false
        }
    }


    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
        isPaused = true
    }
    private fun setUpMedia() {
        mediaPlayer = MediaPlayer.create(this, R.raw.theme)
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }


}