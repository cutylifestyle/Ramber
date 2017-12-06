package com.sixin.ramber.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixin.ramber.R;
import com.sixin.ramber.adapters.HomeVPAdatper;

/**
 * @author zhou
 */
public class MainFragment extends Fragment {

    //TODO 考虑构建一个BaseFragment

    private Toolbar mTLLibrary;
    private ViewPager mVPLibrary;
    private TabLayout mTabLibrary;

    public MainFragment() {
        // Required empty public constructor
    }

    public static Fragment newIntstance(){
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(rootView);
        setActionBar(rootView);
        //TODO 这种关联的方式对于日后的扩展存在很大的影响
        setViewPager(rootView);
        relationTabLayout(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        mTLLibrary = rootView.findViewById(R.id.toolbar_library);
        mVPLibrary = rootView.findViewById(R.id.vp_library);
        mTabLibrary = rootView.findViewById(R.id.tab_library);
    }

    private void relationTabLayout(View rootView) {
        mTabLibrary.setupWithViewPager(mVPLibrary);
    }

    private void setViewPager(View rootView) {
        HomeVPAdatper adapter = new HomeVPAdatper(getChildFragmentManager());
        adapter.addContent(SongsFragment.newInstance(),getString(R.string.songs));
        adapter.addContent(AlbumFragment.newInstance(),getString(R.string.albums));
        adapter.addContent(ArtistFragment.newInstance(),getString(R.string.artists));
        mVPLibrary.setAdapter(adapter);
    }

    private void setActionBar(View rootView) {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mTLLibrary);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
