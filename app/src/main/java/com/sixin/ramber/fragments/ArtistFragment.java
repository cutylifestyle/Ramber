package com.sixin.ramber.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixin.ramber.Config;
import com.sixin.ramber.R;
import com.sixin.ramber.presenters.ArtistLoadPresenter;
import com.sixin.ramber.views.IArtistLoadView;

/**
 * @author zhou
 */
public class ArtistFragment extends Fragment implements IArtistLoadView{

    private RecyclerView mRLVArtist;

    private ArtistLoadPresenter artistLoadPresenter = new ArtistLoadPresenter(this);

    public ArtistFragment() {
        // Required empty public constructor
    }

    public static ArtistFragment newInstance() {
        return new ArtistFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);
        initViews(rootView);
        configRecyclerView();
        artistLoadPresenter.loadArtists(getContext());
        return rootView;
    }

    private void configRecyclerView() {
        mRLVArtist.setLayoutManager(new GridLayoutManager(getContext(), Config.SPAN_COUNT));
    }

    private void initViews(View rootView) {
        mRLVArtist = rootView.findViewById(R.id.rlv_music);
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRLVArtist.setAdapter(adapter);
    }
}
