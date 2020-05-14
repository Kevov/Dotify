package com.example.dotify.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import com.example.dotify.R
import kotlinx.android.synthetic.main.activity_now_playing.*
import kotlin.random.Random

class NowPlayingFragment: Fragment() {
    private lateinit var selectedSong: Song
    private var viewCount: Int = 0

    companion object {
        const val NOW_PLAYING_KEY: String = "now_playing_key"
        val TAG: String = NowPlayingFragment::class.java.simpleName

        fun getInstance(song: Song): NowPlayingFragment {
            return NowPlayingFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(NOW_PLAYING_KEY, song)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewCount = Random.nextInt(1, 100000)

        arguments?.let { args ->
            with (args) {
                getParcelable<Song>(NOW_PLAYING_KEY)?.let { song ->
                    selectedSong = song
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.activity_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        }
    }

    private fun populateSong() {
        ivAlbumCover.setImageResource(selectedSong.largeImageID)
        tvTitle.text = selectedSong.title
        tvArtist.text = selectedSong.artist
    }

    private fun fnPlaySong(view: View) {
        viewCount++
        populateViewCount()
    }

    private fun fnPrevSong(view: View) {
        toastNotification("Skipping to previous track")
    }

    private fun fnNextSong(view: View) {
        toastNotification("Skipping to next track")
    }

    private fun fnChangeUser(view: View) {
        when {
            btnChangeUser.text == "Change User" -> {
                btnChangeUser.text = "Apply"
                changeUsernameEditVisibility(View.INVISIBLE, View.VISIBLE)
            }
            etEditUsername.text.toString() == "" -> {
                toastNotification("Username cannot be empty")
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

    private fun toastNotification(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun changeUsernameEditVisibility(username: Int, editName: Int) {
        tvUsername.visibility = username
        etEditUsername.visibility = editName
    }
}