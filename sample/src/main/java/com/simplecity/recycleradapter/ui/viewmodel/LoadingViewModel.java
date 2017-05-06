package com.simplecity.recycleradapter.ui.viewmodel;

import android.view.View;
import android.view.ViewGroup;

import com.simplecity.recycleradapter.R;
import com.simplecityapps.recycler_adapter.model.BaseViewModel;
import com.simplecityapps.recycler_adapter.recyclerview.BaseViewHolder;


public class LoadingViewModel extends BaseViewModel<String, LoadingViewModel.ViewHolder> {

    @Override
    public int getViewType() {
        return getLayoutResId();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.list_item_loading;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(createView(parent));
    }

    static class ViewHolder extends BaseViewHolder<LoadingViewModel> {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
