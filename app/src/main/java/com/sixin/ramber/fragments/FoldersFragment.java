package com.sixin.ramber.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixin.ramber.R;


public class FoldersFragment extends Fragment {

    private Toolbar mTLFolders;
    private RecyclerView mRLVFolders;

    public FoldersFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new FoldersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_folders, container, false);
        initViews(rootView);
        setActionBar();
        return rootView;
    }

    private void initViews(View rootView) {
        mTLFolders = rootView.findViewById(R.id.toolbar_folders);
        mRLVFolders = rootView.findViewById(R.id.rlv_folders);
    }

    private void setActionBar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mTLFolders);
        ActionBar actionBar =  ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }


}
