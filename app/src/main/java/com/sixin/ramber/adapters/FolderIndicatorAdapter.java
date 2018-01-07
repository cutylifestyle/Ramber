package com.sixin.ramber.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sixin.ramber.R;

import java.util.List;

/**
 * @author zhou
 */

public class FolderIndicatorAdapter extends RecyclerView.Adapter<FolderIndicatorAdapter.FolderIndicatorViewHolder>{

    private List<String> data;

    public FolderIndicatorAdapter(List<String> data){
        this.data = data;
    }


    @Override
    public FolderIndicatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FolderIndicatorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_indicator_item, parent, false));
    }

    @Override
    public void onBindViewHolder(FolderIndicatorViewHolder holder, int position) {
        holder.mTvDirName.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
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
