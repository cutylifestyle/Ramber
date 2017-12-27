package com.sixin.ramber.presenters;

import android.content.Context;
import android.os.AsyncTask;

import com.sixin.ramber.models.Playlist;
import com.sixin.ramber.utils.PlaylistLoadUtil;
import com.sixin.ramber.views.IPlaylistLoadView;

import java.util.List;

/**
 * @author zhou
 */

public class PlaylistLoadPresenter {

    private IPlaylistLoadView playlistLoadView;

    public PlaylistLoadPresenter(IPlaylistLoadView playlistLoadView){
        this.playlistLoadView = playlistLoadView;
    }

    public void loadPlayLists(Context context){
        new PlaylistLoadAsyn().execute(context.getApplicationContext());
    }

    class PlaylistLoadAsyn extends AsyncTask<Context,Void,List<Playlist>>{

        @Override
        protected List<Playlist> doInBackground(Context... contexts) {
            return PlaylistLoadUtil.getPlayLists(contexts[0]);
        }

        @Override
        protected void onPostExecute(List<Playlist> playlists) {
            playlistLoadView.setViewPagerAdapter(playlists);
        }
    }
}
