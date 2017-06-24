package com.zwonb.rvitemdecoration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zyb on 2017/6/23.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyHolder> {

    private Context mContext;
    private List<String> mList;

    public RVAdapter(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public RVAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_text, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RVAdapter.MyHolder holder, int position) {
        holder.name.setText(mList.get(position));
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, Main3Activity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {

        private TextView name;

        MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_rv_text);
        }
    }
}
