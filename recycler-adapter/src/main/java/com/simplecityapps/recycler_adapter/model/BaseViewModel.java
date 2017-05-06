package com.simplecityapps.recycler_adapter.model;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.simplecityapps.recycler_adapter.recyclerview.BaseViewHolder;

import java.util.List;

public abstract class BaseViewModel<T, VH extends BaseViewHolder> implements
        ViewModel<T, VH>,
        ContentsComparator {

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

    @Override
    public boolean areContentsEqual(Object other) {
        return equals(other);
    }

    @Nullable
    @Override
    public T getItem() {
        return null;
    }

    @Override
    public int getSpanSize(int spanCount) {
        return spanCount;
    }
}