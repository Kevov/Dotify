package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var viewCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populateViewCount()
    }

    fun fnPlaySong(view: View) {
        Log.i("dotify", "play pressed")
        viewCount++
        populateViewCount()
    }

    fun fnPrevSong(view: View) {
        Log.i("dotify", "prev pressed")
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun fnNextSong(view: View) {
        Log.i("dotify", "next pressed")
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    //Helper functions
    private fun populateViewCount() {
        val tvViewCount = findViewById<TextView>(R.id.tvViewCount)
        tvViewCount.text = "$viewCount plays"
    }
}
