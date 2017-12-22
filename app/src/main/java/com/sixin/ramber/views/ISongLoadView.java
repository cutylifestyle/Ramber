package com.sixin.ramber.views;

import com.sixin.ramber.adapters.SongLoadAdapter;
import com.sixin.ramber.models.Song;

import java.util.ArrayList;

/**
 * @author zhou
 */

public interface ISongLoadView {

    void setRecyclerViewAdapter(SongLoadAdapter songLoadAdapter);

}
