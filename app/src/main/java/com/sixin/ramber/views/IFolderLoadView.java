package com.sixin.ramber.views;


import java.io.File;
import java.util.List;

/**
 * @author zhou
 */

public interface IFolderLoadView {

    void showProgress();

    void dismissProgress();

    void notifyFoldersDataSetChanged(List<File> files);

    void notifyIndicatorDataSetChanged(File dirFile);
}
