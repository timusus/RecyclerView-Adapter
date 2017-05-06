package com.simplecity.recycleradapter.ui.viewmodel;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplecity.recycleradapter.R;
import com.simplecityapps.recycler_adapter.model.BaseViewModel;
import com.simplecityapps.recycler_adapter.recyclerview.BaseViewHolder;

public class ItemViewModel extends BaseViewModel<String, ItemViewModel.ViewHolder> {

    private String text;

    public ItemViewModel(String text) {
        this.text = text;
    }

    @Nullable
    @Override
    public String getItem() {
        return text;
    }

    @Override
    public int getViewType() {
        return getLayoutResId();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.list_item;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(), parent, false));
    }

    @Override
    public void bindView(ViewHolder holder) {
        super.bindView(holder);

        holder.textView.setText(text);
    }

    static class ViewHolder extends BaseViewHolder<ItemViewModel> {

        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemViewModel itemViewModel = (ItemViewModel) o;

        return text != null ? text.equals(itemViewModel.text) : itemViewModel.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}