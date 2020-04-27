package com.example.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song

class SongListAdapter(private val listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvSongName by lazy { itemView.findViewById<TextView>(R.id.tvSongName) }
        private val tvArtist by lazy {itemView.findViewById<TextView>(R.id.tvArtist)}
        private val ivAlbumCover by lazy {itemView.findViewById<ImageView>(R.id.ivAlbumCover)}
        fun bind(song: Song) {
            tvSongName.text = song.title;
            tvArtist.text = song.artist;
            ivAlbumCover.setImageResource(song.smallImageID)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]
        holder.bind(song)
    }

}