package com.simplecity.recycleradapter.ui.viewmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplecity.recycleradapter.R;
import com.simplecityapps.recycler_adapter.model.BaseViewModel;
import com.simplecityapps.recycler_adapter.recyclerview.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubheaderView extends BaseViewModel<SubheaderView.ViewHolder> {

    private String title;

    public SubheaderView(String title) {
        this.title = title;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.list_item_subheader;
    }

    @Override
    public SubheaderView.ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(createView(parent));
    }

    @Override
    public void bindView(ViewHolder holder) {
        super.bindView(holder);

        holder.textView.setText(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubheaderView that = (SubheaderView) o;

        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.textView)
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }
}
