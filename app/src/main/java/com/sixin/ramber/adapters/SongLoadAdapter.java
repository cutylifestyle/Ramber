package com.sixin.ramber.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixin.ramber.R;
import com.sixin.ramber.models.Song;

import java.util.ArrayList;

/**
 * @author zhou
 */

public class SongLoadAdapter extends RecyclerView.Adapter<SongLoadAdapter.SongLoadViewHolder> {

    private ArrayList<Song> data;

    public SongLoadAdapter(ArrayList<Song> data){
        this.data = data;
    }

    @Override
    public SongLoadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongLoadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_recycler_item, null));
    }

    @Override
    public void onBindViewHolder(SongLoadViewHolder holder, int position) {
        Song song = data.get(position);
        holder.mTvSongTitle.setText(song.getTitle());
        holder.mTvSongArtist.setText(song.getArtistName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SongLoadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mImgAlbumArt;
        TextView mTvSongTitle;
        TextView mTvSongArtist;

        private SongLoadViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
            itemView.setOnClickListener(this);
        }

        private void initViews(View itemView) {
            mImgAlbumArt = itemView.findViewById(R.id.img_album_art);
            mTvSongTitle = itemView.findViewById(R.id.tv_song_title);
            mTvSongArtist = itemView.findViewById(R.id.tv_song_artist);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
