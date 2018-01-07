package com.sixin.ramber.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixin.ramber.R;
import com.sixin.ramber.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 */

public class FolderLoadAdapter extends RecyclerView.Adapter<FolderLoadAdapter.FolderLoadViewHolder> {
    // TODO: 2018/1/7 上拉的时候，最上部的阴影要去掉 indicator需要增加底线
    private List<File> data = new ArrayList<>();

    private OnFoldersItemClickListener listener;

    public FolderLoadAdapter(){}

    public void setOnFoldersItemClickListener(OnFoldersItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public FolderLoadAdapter.FolderLoadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FolderLoadAdapter.FolderLoadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(FolderLoadAdapter.FolderLoadViewHolder holder, int position) {
        final File file = data.get(position);
        if(FileUtil.isDir(file)){
            holder.mImgFolder.setImageResource(R.drawable.ic_folder_open_black_24dp);
        }else{
            holder.mImgFolder.setImageResource(R.drawable.ic_file_black_24dp);
        }
        holder.mTvFolderName.setText(file.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onFoldersItemClick(v,file);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void replaceData(List<File> data){
        this.data.clear();
        this.data.addAll(data);
    }

    public interface OnFoldersItemClickListener{

        void onFoldersItemClick(View v, File file);

    }

    class FolderLoadViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImgFolder;
        private TextView mTvFolderName;

        FolderLoadViewHolder(View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            mImgFolder = itemView.findViewById(R.id.img_folder);
            mTvFolderName = itemView.findViewById(R.id.tv_name_folder);
        }
    }

}
