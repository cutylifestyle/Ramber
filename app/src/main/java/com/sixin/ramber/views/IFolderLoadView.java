package com.sixin.ramber.views;

import android.support.v7.widget.RecyclerView;

/**
 * @author zhou
 */

public interface IFolderLoadView {

    void setAdapter(RecyclerView.Adapter adapter);

    void showProgress();

    void dismissProgress();

}
