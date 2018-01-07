package com.sixin.ramber.presenters;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.sixin.ramber.adapters.FolderLoadAdapter;
import com.sixin.ramber.utils.EnvironmentUtil;
import com.sixin.ramber.utils.FileUtil;
import com.sixin.ramber.views.IFolderLoadView;

import java.io.File;
import java.util.List;

/**
 * @author zhou
 */

public class FolderLoadPresenter {

    private IFolderLoadView folderLoadView;

    public FolderLoadPresenter(IFolderLoadView view){
        folderLoadView =view;
    }

    public void loadFolders(){
        new FolderLoadAsyn().execute();
    }

    private class FolderLoadAsyn extends AsyncTask<Void,Void,List<File>>{

        @Override
        protected void onPreExecute() {
            folderLoadView.showProgress();
        }

        @Override
        protected List<File> doInBackground(Void... voids) {
            return  FileUtil.listFilesInDir(EnvironmentUtil.getExternalStorageDirectoryPath());
        }

        @Override
        protected void onPostExecute(List<File> files) {
            folderLoadView.dismissProgress();
            if(files != null){
                RecyclerView.Adapter adapter = new FolderLoadAdapter(files);
                folderLoadView.setAdapter(adapter);
            }
        }
    }

}
