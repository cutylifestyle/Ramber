package com.sixin.ramber.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixin.ramber.R;
import com.sixin.ramber.adapters.PlaylistVPAdapter;
import com.sixin.ramber.models.Playlist;
import com.sixin.ramber.presenters.PlaylistLoadPresenter;
import com.sixin.ramber.views.IPlaylistLoadView;

import java.util.List;


public class PlayListFragment extends Fragment implements IPlaylistLoadView{
    // TODO: 2017/12/27 toolBar最后变成一个，不要在每个碎片中都放置一个toolBar
    private static final String TAG = PlayListFragment.class.getName();
    private Toolbar mTLPlaylist;
    private ViewPager mVPPlaylist;

    private PlaylistLoadPresenter mPlaylistLoadPresenter = new PlaylistLoadPresenter(this);

    public PlayListFragment() {
        // Required empty public constructor
    }


    public static Fragment newInstance() {
        return new PlayListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play_list, container, false);
        initViews(rootView);
        setActionBar();
        configViewPager();
        mPlaylistLoadPresenter.loadPlayLists(getContext());
        return rootView;
    }

    // TODO: 2017/12/27 actionBar这儿的代码出现了严重的重复，想办法融合
    private void setActionBar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mTLPlaylist);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void initViews(View rootView) {
        mTLPlaylist = rootView.findViewById(R.id.toolbar_playlist);
        mVPPlaylist = rootView.findViewById(R.id.vp_playlist);
    }

    private void configViewPager() {
        mVPPlaylist.setOffscreenPageLimit(2);
        mVPPlaylist.setPageMargin(-100);
    }

    @Override
    public void setViewPagerAdapter(List<Playlist> data) {
        mVPPlaylist.setAdapter(new PlaylistVPAdapter(getActivity().getSupportFragmentManager(),
                data));
    }
}
