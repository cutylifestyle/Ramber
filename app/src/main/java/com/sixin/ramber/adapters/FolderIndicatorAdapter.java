package com.sixin.ramber.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sixin.ramber.Config;
import com.sixin.ramber.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 */

public class FolderIndicatorAdapter extends RecyclerView.Adapter<FolderIndicatorAdapter.FolderIndicatorViewHolder>{

    private List<File> data = new ArrayList<>();

    private OnIndicatorItemClickListener listener;

    public FolderIndicatorAdapter(){}

    public void setOnIndicatorItemClickListener(OnIndicatorItemClickListener listener){
        this.listener = listener;
    }

    // TODO: 2018/1/7 这块的策略需要改变,而且添加集合工具类
    public void removeDataAfterPosition(int position){

        List<File> dataNew = new ArrayList<>();

        for(int i = 0;i < position+1;i++){
            dataNew.add(data.get(i));
        }
        data.clear();

        data.addAll(dataNew);
    }

    public void addData(File dirFile){
        if(!data.contains(dirFile)){
            data.add(dirFile);
        }
    }

    @Override
    public FolderIndicatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FolderIndicatorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_indicator_item, parent, false));
    }

    @Override
    public void onBindViewHolder(FolderIndicatorViewHolder holder, final int position) {
        final File dirFile = data.get(position);

        final int position1 = position;

        if(position == 0){
            holder.mTvDirName.setText(Config.INTERNAL_STORAGE);
        }else {
            holder.mTvDirName.setText(dirFile.getName());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onIndicatorItemClick(v, dirFile, position1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnIndicatorItemClickListener{
        void onIndicatorItemClick(View v, File dirFile, int position);
    }

    class FolderIndicatorViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvDirName;

        public FolderIndicatorViewHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            mTvDirName = itemView.findViewById(R.id.tv_dir_name);
        }
    }

}
