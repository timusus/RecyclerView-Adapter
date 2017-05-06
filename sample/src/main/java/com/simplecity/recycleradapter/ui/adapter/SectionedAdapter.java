package com.simplecity.recycleradapter.ui.adapter;

import android.support.annotation.NonNull;

import com.simplecity.recycleradapter.ui.viewmodel.SectionedViewModel;
import com.simplecityapps.recycler_adapter.adapter.ViewModelAdapter;
import com.simplecityapps.recycler_adapter.model.ViewModel;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

public class SectionedAdapter extends ViewModelAdapter implements FastScrollRecyclerView.SectionedAdapter {
    @NonNull
    @Override
    public String getSectionName(int position) {

        ViewModel item = items.get(position);
        if (item instanceof SectionedViewModel) {
            return ((SectionedViewModel) item).getSectionName();
        }

        return "";
    }
}
