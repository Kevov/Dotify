package com.example.dotify.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import com.example.dotify.model.SongListAdapter
import com.example.dotify.R
import com.example.dotify.model.OnSongClickListener
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListFragment: Fragment() {
    private lateinit var songListAdapter: SongListAdapter
    private lateinit var listOfSongs: MutableList<Song>
    private lateinit var onSongClickListener: OnSongClickListener

    companion object {
        const val SONG_LIST_KEY: String = "song_list_key"

        fun getInstance(listOfSongs: List<Song>): SongListFragment {
            return SongListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(SONG_LIST_KEY, ArrayList(listOfSongs))
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            with (args) {
                getParcelableArrayList<Song>(SONG_LIST_KEY)?.let { list ->
                    listOfSongs = list.toMutableList()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.activity_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songListAdapter = SongListAdapter(listOfSongs)
        rvMusicList.adapter = songListAdapter

        songListAdapter.onSongClickListener = {song ->
            onSongClickListener.onSongClicked(song)
        }

        /*btnShuffle.setOnClickListener{*/
        /*    shuffleMusicList()*/
        /*}*/
    }

    /*private fun populateActionBarAndStoreSong(song: Song) {
        val songName = song.title
        val artist = song.artist
        storedSong = song
        tvActionBar.visibility = View.VISIBLE
        tvActionBar.text = getString(R.string.actionBarText).format(songName, artist)
    }*/

    fun shuffleMusicList() {
        val shuffledList = listOfSongs.apply { shuffle() }
        songListAdapter.change(shuffledList)
    }
}