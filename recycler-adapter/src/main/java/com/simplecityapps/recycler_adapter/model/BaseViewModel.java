package com.simplecityapps.recycler_adapter.model;

import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplecityapps.recycler_adapter.recyclerview.BaseViewHolder;

import java.util.List;

public abstract class BaseViewModel<T, VH extends BaseViewHolder> implements
        ViewModel<T, VH>,
        ContentsComparator {

    @Override
    public int getViewType() {
        return getLayoutResId();
    }

    @CallSuper
    @Override
    public void bindView(VH holder) {
        holder.bind(this);
    }

    @Override
    public void bindView(VH holder, int position, List payloads) {
        if (payloads.isEmpty()) {
            bindView(holder);
        }
    }

    public View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(), parent, false);
    }

    @Override
    public boolean areContentsEqual(Object other) {
        return equals(other);
    }

    @Override
    public int getSpanSize(int spanCount) {
        return spanCount;
    }
}