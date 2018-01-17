package com.sixin.ramber.adapters;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sixin.ramber.R;
import com.sixin.ramber.models.Album;
import com.sixin.ramber.utils.ComprehensiveUtil;

import java.util.List;

/**
 * @author zhou
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    // TODO: 2018/1/17 Glide源码分析
    // TODO: 2018/1/17 阅读recyclerView源码，解决图片以及footer颜色的问题
    // TODO: 2018/1/17 调色板源码分析
    private List<Album> data;

    public AlbumAdapter(@NonNull List<Album> data){
        this.data = data;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.album_recycler_item
                        ,parent
                        , false));
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album album = data.get(position);
        setImgAndFooter(holder, album);
        holder.mTvAlbumTitle.setText(album.getTitle());
        holder.mTvAlbumArtist.setText(album.getArtistName());

    }

    /**
     * 设置专辑图片和footer的背景颜色
     * */
    private void setImgAndFooter(final AlbumViewHolder holder, Album album) {
        Glide.with(holder.itemView.getContext())
                .load(ComprehensiveUtil.getAlbumArtUri(album.getId()))
                .asBitmap()
                .placeholder(R.drawable.ic_empty_music)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder.mImgAlbum.setImageBitmap(resource);
                        setFooterBackground(resource, holder);
                    }
                });
    }

    /**
     * 设置footer的背景色
     * */
    private void setFooterBackground(Bitmap resource, final AlbumViewHolder holder) {
        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                if(swatch != null){
                    int backgroundColor = swatch.getRgb();
                    holder.mLLFooter.setBackgroundColor(backgroundColor);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImgAlbum;
        private TextView mTvAlbumTitle;
        private TextView mTvAlbumArtist;
        private LinearLayout mLLFooter;

        private AlbumViewHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            mImgAlbum = itemView.findViewById(R.id.img_album_art);
            mTvAlbumTitle = itemView.findViewById(R.id.tv_album_title);
            mTvAlbumArtist = itemView.findViewById(R.id.tv_album_artist);
            mLLFooter = itemView.findViewById(R.id.ll_footer);
        }
    }

}
