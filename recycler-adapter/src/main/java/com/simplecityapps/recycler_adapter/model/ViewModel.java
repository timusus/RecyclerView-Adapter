package com.simplecityapps.recycler_adapter.model;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public interface ViewModel<T, H extends RecyclerView.ViewHolder> extends ContentsComparator {

    int getViewType();

    @LayoutRes
    int getLayoutResId();

    void bindView(H holder);

    void bindView(H holder, int position, List payloads);

    H createViewHolder(ViewGroup parent);

    int getSpanSize(int spanCount);
}