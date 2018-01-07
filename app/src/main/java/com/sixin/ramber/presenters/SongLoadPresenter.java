package com.sixin.ramber.presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.sixin.ramber.adapters.SongLoadAdapter;
import com.sixin.ramber.models.Song;
import com.sixin.ramber.utils.SongLoadUtil;
import com.sixin.ramber.views.ISongLoadView;

import java.util.ArrayList;

/**
 * @author zhou
 */

public class SongLoadPresenter {

    private ISongLoadView songLoadView;

    public SongLoadPresenter(ISongLoadView songLoadView){
        this.songLoadView = songLoadView;
    }

    public void loadSongs(Context context){
        new SongLoadAsync().execute(context.getApplicationContext());
    }

    // TODO: 2017/12/22 关于这个警告的处理方式
    private class SongLoadAsync extends AsyncTask<Context,Void,ArrayList<Song>>{

        @Override
        protected ArrayList<Song> doInBackground(Context... contexts) {
            return SongLoadUtil.getAllSongs(contexts[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Song> songs) {
            SongLoadAdapter songLoadAdapter = new SongLoadAdapter(songs);
            songLoadView.setRecyclerViewAdapter(songLoadAdapter);
        }
    }

}
