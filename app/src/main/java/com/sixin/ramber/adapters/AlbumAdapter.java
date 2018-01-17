package com.sixin.ramber.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixin.ramber.R;
import com.sixin.ramber.models.Album;

import java.util.List;

/**
 * @author zhou
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> data;

    public AlbumAdapter(@NonNull List<Album> data){
        this.data = data;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.album_recycler_item,
                parent,false));
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder{

        private AlbumViewHolder(View itemView) {
            super(itemView);
        }
    }

}
