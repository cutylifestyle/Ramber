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
import com.sixin.ramber.presenters.AlbumLoadPresenter;
import com.sixin.ramber.views.IAlbumLoadView;

/**
 * @author zhou
 */
public class AlbumFragment extends Fragment implements IAlbumLoadView{

    private RecyclerView mRLVAlbum;

    private AlbumLoadPresenter albumLoadPresenter = new AlbumLoadPresenter(this);

    public AlbumFragment() {
        // Required empty public constructor
    }

    public static AlbumFragment newInstance() {
        return new AlbumFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);
        initViews(rootView);
        configRecyclerView();
        albumLoadPresenter.loadAlbums(getContext());
        return rootView;
    }

    // TODO: 2018/1/17 自定义分割线
    private void configRecyclerView() {
        mRLVAlbum.setLayoutManager(new GridLayoutManager(getContext(), Config.SPAN_COUNT));
    }

    private void initViews(View rootView) {
        mRLVAlbum = rootView.findViewById(R.id.rlv_music);
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRLVAlbum.setAdapter(adapter);
    }
}
