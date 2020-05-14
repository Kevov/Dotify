package com.example.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.R
import com.example.dotify.fragment.NowPlayingFragment
import com.example.dotify.fragment.SongListFragment
import com.example.dotify.model.OnSongClickListener
import kotlinx.android.synthetic.main.activity_main_fragment.*

class MainFragmentActivity : AppCompatActivity(), OnSongClickListener {
    private var listOfSongs = SongDataProvider.getAllSongs()
    private var selectedSong: Song? = null
    private var viewCount: Int = 0

    companion object {
        const val SELECTED_SONG_KEY = "selected_song_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragment)

        val hasSongListFragment: Boolean = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) != null
        val hasNowPlayingFragment: Boolean = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) != null

        if (savedInstanceState != null) {
            selectedSong = savedInstanceState.getParcelable(SELECTED_SONG_KEY)
            populateActionBar()
        }


        if (!hasSongListFragment && !hasNowPlayingFragment) {
            val songListFragment = SongListFragment.getInstance(listOfSongs)

            Log.i("DETAIL", "false")

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragMusicList, songListFragment, SongListFragment.TAG)
                .addToBackStack(SongListFragment.TAG)
                .commit()
        } else if (hasNowPlayingFragment) {
            Log.i("songlist", "hello")
            clMiniPlayer.visibility = View.GONE
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = (supportFragmentManager.backStackEntryCount > 1)
            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

        btnShuffle.setOnClickListener {
            val songListFragment = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as SongListFragment
            songListFragment.shuffleMusicList()
        }

        tvActionBar.setOnClickListener {
            showNowPlayingFragment()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        clMiniPlayer.visibility = View.VISIBLE
        supportFragmentManager.popBackStack()
        return super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putParcelable(SELECTED_SONG_KEY, selectedSong)
        }
        super.onSaveInstanceState(outState)
    }

    private fun showNowPlayingFragment() {
        clMiniPlayer.visibility = View.GONE
        selectedSong?.let { song ->
            val nowPlayingFragment = NowPlayingFragment.getInstance(song)

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragMusicList, nowPlayingFragment, NowPlayingFragment.TAG)
                .addToBackStack(NowPlayingFragment.TAG)
                .commit()
        }
    }

    private fun populateActionBar() {
        selectedSong?.let { song ->
            tvActionBar.visibility = View.VISIBLE
            tvActionBar.text = "%s - %s".format(song.title, song.artist)
        }
    }

    override fun onSongClicked(song: Song) {
        selectedSong = song
        populateActionBar()
    }
}
