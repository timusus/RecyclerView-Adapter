package com.simplecityapps.recycler_adapter.recyclerview;

import android.support.v7.widget.GridLayoutManager;

import com.simplecityapps.recycler_adapter.adapter.ItemAdapter;
import com.simplecityapps.recycler_adapter.model.ViewModel;

import java.util.List;

public class SpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private ItemAdapter itemAdapter;
    private int spanCount;

    public SpanSizeLookup(ItemAdapter itemAdapter, int spanCount) {
        this.itemAdapter = itemAdapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {

        List<ViewModel> items = itemAdapter.items;
        if (position > 0 && position < items.size()) {
            return items.get(position).getSpanSize(spanCount);
        }

        return 1;
    }
}
