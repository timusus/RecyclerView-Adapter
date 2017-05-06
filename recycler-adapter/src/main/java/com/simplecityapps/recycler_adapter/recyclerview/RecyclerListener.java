package com.simplecityapps.recycler_adapter.recyclerview;

import android.support.v7.widget.RecyclerView;

public class RecyclerListener implements RecyclerView.RecyclerListener {

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof BaseViewHolder) {
            ((BaseViewHolder) holder).recycle();
        }
    }
}