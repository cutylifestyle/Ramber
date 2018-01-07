package com.sixin.ramber.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sixin.ramber.R;
import com.sixin.ramber.adapters.FolderIndicatorAdapter;
import com.sixin.ramber.presenters.FolderLoadPresenter;
import com.sixin.ramber.views.IFolderLoadView;

import java.util.ArrayList;
import java.util.List;


public class FoldersFragment extends Fragment implements IFolderLoadView{

    // TODO: 2018/1/7 自定义进度条

    private Toolbar mTLFolders;
    private RecyclerView mRLVFolders;
    private RecyclerView mRLVIndicator;
    private ProgressBar mProBarFolders;

    private FolderLoadPresenter mFolderLoadPresenter = new FolderLoadPresenter(this);;

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
        configRLVIndicator();
        configRLVFolders();
        List<String> data = new ArrayList<>();
        data.add(getResources().getString(R.string.internal_storage));
        mRLVIndicator.setAdapter(new FolderIndicatorAdapter(data));
        mFolderLoadPresenter.loadFolders();

        return rootView;
    }

    private void initViews(View rootView) {
        mTLFolders = rootView.findViewById(R.id.toolbar_folders);
        mRLVFolders = rootView.findViewById(R.id.rlv_folders);
        mRLVIndicator = rootView.findViewById(R.id.rlv_indicator);
        mProBarFolders = rootView.findViewById(R.id.proBar_folders);
    }

    private void setActionBar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mTLFolders);
        ActionBar actionBar =  ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void configRLVIndicator() {
        mRLVIndicator.setLayoutManager(new GridLayoutManager(getContext(),1, GridLayoutManager.HORIZONTAL,false));
    }

    private void configRLVFolders() {
        mRLVFolders.setLayoutManager(new LinearLayoutManager(getContext()));
        mRLVFolders.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        mRLVFolders.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        mProBarFolders.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        mProBarFolders.setVisibility(View.INVISIBLE);
    }
}
