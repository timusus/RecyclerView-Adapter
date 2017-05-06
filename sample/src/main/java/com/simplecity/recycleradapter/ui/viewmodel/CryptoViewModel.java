package com.simplecity.recycleradapter.ui.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simplecity.recycleradapter.R;
import com.simplecity.recycleradapter.model.CryptoCurrency;
import com.simplecityapps.recycler_adapter.model.BaseViewModel;
import com.simplecityapps.recycler_adapter.recyclerview.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoViewModel extends BaseViewModel<CryptoCurrency, CryptoViewModel.ViewHolder> implements SectionedViewModel {

    private CryptoCurrency cryptoCurrency;

    public CryptoViewModel(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    @Nullable
    @Override
    public CryptoCurrency getItem() {
        return cryptoCurrency;
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

        holder.textView1.setText(String.format("%s - %s", cryptoCurrency.symbol, cryptoCurrency.name));
        holder.textView2.setText(String.format("Market cap: $%s", cryptoCurrency.marketCap));
        holder.textView3.setText(String.format("7 day change: %s%%", cryptoCurrency.percentChange24h));

        Glide.with(holder.itemView.getContext())
                .load(String.format("https://files.coinmarketcap.com/static/img/coins/64x64/%s.png", cryptoCurrency.name.toLowerCase().replace(" ", "-")))
                .into(holder.imageView);
    }

    @NonNull
    @Override
    public String getSectionName() {
        return cryptoCurrency.rank;
    }

    static class ViewHolder extends BaseViewHolder<CryptoViewModel> {

        @BindView(R.id.image1)
        ImageView imageView;

        @BindView(R.id.text1)
        TextView textView1;

        @BindView(R.id.text2)
        TextView textView2;

        @BindView(R.id.text3)
        TextView textView3;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void recycle() {
            super.recycle();

            Glide.clear(imageView);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CryptoViewModel cryptoViewModel = (CryptoViewModel) o;

        return cryptoCurrency != null ? cryptoCurrency.equals(cryptoViewModel.cryptoCurrency) : cryptoViewModel.cryptoCurrency == null;
    }

    @Override
    public int hashCode() {
        return cryptoCurrency != null ? cryptoCurrency.hashCode() : 0;
    }
}