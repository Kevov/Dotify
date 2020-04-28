package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {
    private lateinit var clActionBar: ConstraintLayout
    private lateinit var  btnShuffle: Button
    private lateinit var tvActionBar: TextView
    private val listOfSong: List<Song> = SongDataProvider.getAllSongs()
    private val songListAdapter = SongListAdapter(listOfSong)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        clActionBar = findViewById<ConstraintLayout>(R.id.clActionBar)
        btnShuffle = findViewById<Button>(R.id.btnShuffle)
        tvActionBar = findViewById<TextView>(R.id.tvActionBar)

        rvMusicList.adapter = songListAdapter

        songListAdapter.onSongClickListener = {song, artist ->
            populateActionBar(song, artist)
        }

        btnShuffle.setOnClickListener{
            shuffleMusicList()
        }
    }

    private fun populateActionBar(song: String, artist: String) {
        tvActionBar.text = getString(R.string.actionBarText).format(song, artist)
    }

    private fun shuffleMusicList() {
        tvActionBar.text = ""
        val shuffledList = listOfSong.toMutableList().apply { shuffle() }
        songListAdapter.change(shuffledList)
    }
}
