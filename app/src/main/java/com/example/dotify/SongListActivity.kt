package com.example.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.MainActivity.Companion.ARTIST_KEY
import com.example.dotify.MainActivity.Companion.COVER_KEY
import com.example.dotify.MainActivity.Companion.NAME_KEY
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {
    private lateinit var  btnShuffle: Button
    private lateinit var tvActionBar: TextView
    private val listOfSong: List<Song> = SongDataProvider.getAllSongs()
    private val songListAdapter = SongListAdapter(listOfSong)
    private lateinit var storedSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        btnShuffle = findViewById<Button>(R.id.btnShuffle)
        tvActionBar = findViewById<TextView>(R.id.tvActionBar)

        rvMusicList.adapter = songListAdapter

        songListAdapter.onSongClickListener = {song ->
            populateActionBarAndStoreSong(song)
        }

        btnShuffle.setOnClickListener{
            shuffleMusicList()
        }

        tvActionBar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(NAME_KEY, storedSong.title)
            intent.putExtra(ARTIST_KEY, storedSong.artist)
            intent.putExtra(COVER_KEY, storedSong.largeImageID)
            startActivity(intent)
        }
    }

    private fun populateActionBarAndStoreSong(song: Song) {
        val songName = song.title
        val artist = song.artist
        storedSong = song
        tvActionBar.visibility = View.VISIBLE
        tvActionBar.text = getString(R.string.actionBarText).format(songName, artist)
    }

    private fun shuffleMusicList() {
        tvActionBar.visibility = View.INVISIBLE
        val shuffledList = listOfSong.toMutableList().apply { shuffle() }
        songListAdapter.change(shuffledList)
    }
}
