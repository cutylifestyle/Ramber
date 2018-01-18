package com.sixin.ramber.presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.sixin.ramber.adapters.ArtistAdapter;
import com.sixin.ramber.models.Artist;
import com.sixin.ramber.utils.ArtistLoadUtil;
import com.sixin.ramber.views.IArtistLoadView;

import java.util.List;

/**
 * @author zhou
 */

public class ArtistLoadPresenter {

    private IArtistLoadView artistLoadView;

    public ArtistLoadPresenter(@NonNull IArtistLoadView view){
        artistLoadView = view;
    }

    public void loadArtists(Context context){
        new ArtistAsyn().execute(context.getApplicationContext());
    }

    private class ArtistAsyn extends AsyncTask<Context,Void,List<Artist>>{

        @Override
        protected List<Artist> doInBackground(Context... contexts) {
            return ArtistLoadUtil.getAllArtists(contexts[0]);
        }

        @Override
        protected void onPostExecute(List<Artist> artists) {
            ArtistAdapter adapter = new ArtistAdapter(artists);
            artistLoadView.setRecyclerViewAdapter(adapter);
        }
    }

}
