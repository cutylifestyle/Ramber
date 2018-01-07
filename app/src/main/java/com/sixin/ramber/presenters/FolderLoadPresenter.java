package com.sixin.ramber.presenters;

import android.os.AsyncTask;

import com.sixin.ramber.adapters.FolderIndicatorAdapter;
import com.sixin.ramber.utils.FileUtil;
import com.sixin.ramber.views.IFolderLoadView;

import java.io.File;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * @author zhou
 */

public class FolderLoadPresenter {

    private IFolderLoadView folderLoadView;

    private File mDirFile;

    public FolderLoadPresenter(IFolderLoadView view){
        folderLoadView =view;
    }

    public void loadFolders(@NonNull File dirFile){
        mDirFile =dirFile;
        new FolderLoadAsyn().execute();
    }

    private class FolderLoadAsyn extends AsyncTask<Void,Void,List<File>>{

        @Override
        protected void onPreExecute() {
            folderLoadView.showProgress();
        }

        @Override
        protected List<File> doInBackground(Void... voids) {
            return  FileUtil.listFilesInDir(mDirFile,false);
        }

        @Override
        protected void onPostExecute(List<File> files) {
            folderLoadView.dismissProgress();
            // TODO: 2018/1/7 需要一个工具类来处理  判空工具类，字符串工具类
            if(files != null){
                folderLoadView.notifyIndicatorDataSetChanged(mDirFile);
                folderLoadView.notifyFoldersDataSetChanged(files);
            }
        }
    }

}
