package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var viewCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populateViewCount()

        ibPrev.setOnClickListener {
            v: View -> fnPrevSong(v)
        }
        ibPlay.setOnClickListener {
            v: View -> fnPlaySong(v)
        }
        ibNext.setOnClickListener {
            v: View -> fnNextSong(v)
        }
        btnChangeUser.setOnClickListener {
            v: View -> fnChangeUser(v)
        }
    }

    private fun fnPlaySong(view: View) {
        Log.i("dotify", "play pressed")
        viewCount++
        populateViewCount()
    }

    private fun fnPrevSong(view: View) {
        Log.i("dotify", "prev pressed")
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun fnNextSong(view: View) {
        Log.i("dotify", "next pressed")
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    private fun fnChangeUser(view: View) {
        Log.i("dotify", "change user")

    }

    //Helper functions
    private fun populateViewCount() {
        tvViewCount.text = "$viewCount plays"
    }
}
