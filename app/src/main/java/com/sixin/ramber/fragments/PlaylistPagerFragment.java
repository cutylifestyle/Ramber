package com.sixin.ramber.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixin.ramber.R;
import com.sixin.ramber.models.Playlist;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaylistPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaylistPagerFragment extends Fragment {

    private static final String ARG_PLAY_LIST = "playlist";
    private static final String ARG_PAGE_NUMBER = "pageNumber";

    private Playlist mPlaylist;
    private int mPageNumber;

    private ImageView mImgPlaylist;
    private TextView mTvName;
    private TextView mTvNumber;
    private TextView mTvSongCount;

    public PlaylistPagerFragment() {
        // Required empty public constructor
    }


    public static Fragment newInstance(Playlist playlist,int pageNumber) {
        PlaylistPagerFragment fragment = new PlaylistPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLAY_LIST, playlist);
        args.putInt(ARG_PAGE_NUMBER,pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlaylist = (Playlist) getArguments().getSerializable(ARG_PLAY_LIST);
            mPageNumber = getArguments().getInt(ARG_PAGE_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_playlist_pager, container, false);
        initViews(rootView);
        setPageContent();
        return rootView;
    }

    private void initViews(View rootView) {
        mImgPlaylist = rootView.findViewById(R.id.img_playlist_page);
        mTvName = rootView.findViewById(R.id.tv_playlist_name);
        mTvSongCount = rootView.findViewById(R.id.tv_playlist_song_count);
        mTvNumber = rootView.findViewById(R.id.tv_playlist_number);
    }

    private void setPageContent() {
        mTvName.setText(mPlaylist.getName());
        mTvNumber.setText(String.valueOf(mPageNumber+1));
    }


}
