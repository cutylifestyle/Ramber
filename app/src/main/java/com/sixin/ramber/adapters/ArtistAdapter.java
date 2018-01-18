package com.sixin.ramber.adapters;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sixin.ramber.R;
import com.sixin.ramber.models.Artist;
import com.sixin.ramber.utils.ComprehensiveUtil;

import java.util.List;

/**
 * @author zhou
 */
// TODO: 2018/1/18 艺术家图片如何加载
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    private List<Artist> data;

    public ArtistAdapter(List<Artist> data){
        this.data = data;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArtistViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.music_recycler_item
                        ,parent
                        , false));
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        Artist artist = data.get(position);
        setImgAndFooter(holder,artist);
        holder.mTvArtistName.setText(artist.getName());
        String musicInfo = artist.getAlbumCount()+"张专辑"+" | "+artist.getSongCount()+"首歌曲";
        holder.mTvMusicInfo.setText(musicInfo);
    }

    /**
     * 设置专辑图片和footer的背景颜色
     * */
    private void setImgAndFooter(final ArtistViewHolder holder, Artist artist) {
        Glide.with(holder.itemView.getContext())
                .load(ComprehensiveUtil.getAlbumArtUri(artist.getId()))
                .asBitmap()
                .error(R.drawable.ic_empty_music)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder.mImgArtist.setImageBitmap(resource);
                        setFooterBackground(resource, holder);
                    }
                });
    }

    /**
     * 设置footer的背景色
     * */
    private void setFooterBackground(Bitmap resource, final ArtistViewHolder holder) {
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

    class ArtistViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImgArtist;
        private TextView mTvArtistName;
        private TextView mTvMusicInfo;
        private LinearLayout mLLFooter;

        private ArtistViewHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            mImgArtist = itemView.findViewById(R.id.img_album_art);
            mTvArtistName = itemView.findViewById(R.id.tv_title);
            mTvMusicInfo = itemView.findViewById(R.id.tv_subTitle);
            mLLFooter = itemView.findViewById(R.id.ll_footer);
        }
    }

}
