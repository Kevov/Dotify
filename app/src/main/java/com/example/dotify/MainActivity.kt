package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var viewCount: Int = Random.nextInt(1, 100000)

    companion object {
        const val NAME_KEY = "NAME_KEY"
        const val ARTIST_KEY = "ARTIST_KEY"
        const val COVER_KEY = "COVER_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_main)
        populateViewCount()
        populateSong()


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
        }*/
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /*private fun populateSong() {
        val ivCover = findViewById<ImageView>(R.id.ivAlbumCover)
        val tvSongName = findViewById<TextView>(R.id.tvTitle)
        val tvArtist = findViewById<TextView>(R.id.tvArtist)

        ivCover.setImageResource(intent.getIntExtra(COVER_KEY, -1))
        tvSongName.text = intent.getStringExtra(NAME_KEY)
        tvArtist.text = intent.getStringExtra(ARTIST_KEY)
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
        when {
            btnChangeUser.text == "Change User" -> {
                btnChangeUser.text = "Apply"
                changeUsernameEditVisibility(View.INVISIBLE, View.VISIBLE)
            }
            etEditUsername.text.toString() == "" -> {
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else -> {
                tvUsername.text = etEditUsername.text.toString()
                btnChangeUser.text = "Change User"
                changeUsernameEditVisibility(View.VISIBLE, View.INVISIBLE)
            }
        }
    }

    //Helper functions
    private fun populateViewCount() {
        tvViewCount.text = "$viewCount plays"
    }

    private fun changeUsernameEditVisibility(username: Int, editName: Int) {
        tvUsername.visibility = username
        etEditUsername.visibility = editName
    }*/
}
