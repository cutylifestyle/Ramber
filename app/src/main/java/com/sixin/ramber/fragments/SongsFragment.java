package com.sixin.ramber.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixin.ramber.R;
import com.sixin.ramber.adapters.SongLoadAdapter;
import com.sixin.ramber.presenters.SongLoadPresenter;
import com.sixin.ramber.views.ISongLoadView;


/**
 * @author zhou
 */
public class SongsFragment extends Fragment implements ISongLoadView{
    // TODO: 2017/12/22 behavior的作用  侧面的自定义控件还没有做

    private SongLoadPresenter songLoadPresenter = new SongLoadPresenter(this);

    private RecyclerView mRlVSongs;

    public SongsFragment() {
        // Required empty public constructor
    }


    public static Fragment newInstance() {
        return new SongsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_songs, container, false);
        initViews(rootView);
        configRecyclerView();
        songLoadPresenter.loadSongs(getContext());
        return rootView;
    }

    private void initViews(View rootView) {
        mRlVSongs = rootView.findViewById(R.id.rlv_songs);
    }

    private void configRecyclerView() {
        mRlVSongs.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlVSongs.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }

    @Override
    public void setRecyclerViewAdapter(SongLoadAdapter songLoadAdapter) {
        mRlVSongs.setAdapter(songLoadAdapter);
    }
}
