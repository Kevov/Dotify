package com.example.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.R
import com.example.dotify.fragment.NowPlayingFragment
import com.example.dotify.fragment.NowPlayingFragment.Companion.NOW_PLAYING_KEY
import com.example.dotify.fragment.SongListFragment
import com.example.dotify.fragment.SongListFragment.Companion.SONG_LIST_KEY
import com.example.dotify.model.OnSongClickListener
import kotlinx.android.synthetic.main.activity_main_fragment.*

class MainFragmentActivity : AppCompatActivity(), OnSongClickListener {
    private lateinit var songListFragment: SongListFragment
    private lateinit var nowPlayingFragment: NowPlayingFragment
    private lateinit var selectedSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragment)

        val listOfSongs = ArrayList<Song>(SongDataProvider.getAllSongs())

        songListFragment = SongListFragment()
        nowPlayingFragment = NowPlayingFragment()

        val songListArgument: Bundle = Bundle().apply {
            putParcelableArrayList(SONG_LIST_KEY, listOfSongs)
        }
        songListFragment.arguments = songListArgument

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragMusicList, songListFragment)
            .commit()

        btnShuffle.setOnClickListener {
            tvActionBar.visibility = View.INVISIBLE
            songListFragment.shuffleMusicList()
        }

        tvActionBar.setOnClickListener {
            showNowPlayingFragment()
        }
    }

    private fun showNowPlayingFragment() {
        clMiniPlayer.visibility = View.GONE

        val songDetailArgument = Bundle().apply {
            putParcelable(NOW_PLAYING_KEY, selectedSong)
        }
        nowPlayingFragment.arguments = songDetailArgument

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragMusicList, nowPlayingFragment)
            .commit()
    }

    override fun onSongClicked(song: Song) {
        selectedSong = song
        tvActionBar.visibility = View.VISIBLE
        tvActionBar.text = "%s - %s".format(song.title, song.artist)
    }
}
