package com.sixin.ramber.views;

import com.sixin.ramber.models.Playlist;

import java.util.List;

/**
 * @author zhou
 */

public interface IPlaylistLoadView {

   void  setViewPagerAdapter(List<Playlist> data);
}
