package com.sixin.ramber.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sixin.ramber.fragments.PlaylistPagerFragment;
import com.sixin.ramber.models.Playlist;

import java.util.List;

/**
 * @author zhou
 */

public class PlaylistVPAdapter extends FragmentStatePagerAdapter {
    // TODO: 2017/12/27 封装一个BaseAdapter出来

    private List<Playlist> data;

    public PlaylistVPAdapter(FragmentManager fm,List<Playlist> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaylistPagerFragment.newInstance(data.get(position),position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
