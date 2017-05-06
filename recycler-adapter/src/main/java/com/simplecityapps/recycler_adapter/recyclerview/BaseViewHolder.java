package com.simplecityapps.recycler_adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.simplecityapps.recycler_adapter.model.ViewModel;

public abstract class BaseViewHolder<VM extends ViewModel> extends RecyclerView.ViewHolder {

    protected VM viewModel;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(VM viewModel) {
        this.viewModel = viewModel;
    }

    public void recycle() {

    }
}