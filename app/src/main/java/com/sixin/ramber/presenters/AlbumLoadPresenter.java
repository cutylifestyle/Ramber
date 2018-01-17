package com.sixin.ramber.presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.sixin.ramber.adapters.AlbumAdapter;
import com.sixin.ramber.models.Album;
import com.sixin.ramber.utils.AlbumLoadUtil;
import com.sixin.ramber.views.IAlbumLoadView;

import java.util.List;

/**
 * @author zhou
 */

public class AlbumLoadPresenter {
    // TODO: 2018/1/17 实现懒加载
    private IAlbumLoadView albumLoadView;

    public AlbumLoadPresenter(@NonNull IAlbumLoadView view){
        albumLoadView =view;
    }

    public void loadAlbums(Context context){
        new AlbumAsyn().execute(context.getApplicationContext());
    }

    private class AlbumAsyn extends AsyncTask<Context,Void,List<Album>>{

        @Override
        protected List<Album> doInBackground(Context... contexts) {
            return AlbumLoadUtil.getAllAlbums(contexts[0]);
        }

        @Override
        protected void onPostExecute(List<Album> albums) {
            AlbumAdapter adapter = new AlbumAdapter(albums);
            albumLoadView.setRecyclerViewAdapter(adapter);
        }
    }

}
