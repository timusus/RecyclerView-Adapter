package com.simplecity.recycleradapter.ui.presenter;


import android.content.Context;

import com.simplecity.recycleradapter.App;
import com.simplecity.recycleradapter.model.CryptoCurrency;
import com.simplecity.recycleradapter.network.CryptoCompareApi;
import com.simplecity.recycleradapter.ui.view.CryptoView;
import com.simplecity.recycleradapter.ui.viewmodel.CryptoViewModel;
import com.simplecity.recycleradapter.ui.viewmodel.SubheaderView;
import com.simplecityapps.recycler_adapter.model.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoPresenter extends Presenter<CryptoView> {

    @Inject CryptoCompareApi cryptoCompareApi;

    public CryptoPresenter(Context context) {
        ((App) context).getAppComponent().inject(this);
    }

    public void getCryptoCurrencies() {

        CryptoView view = getView();
        if (view != null) {
            view.showLoading();
        }

        cryptoCompareApi.getCoinList(100).enqueue(new Callback<List<CryptoCurrency>>() {
            @Override
            public void onResponse(Call<List<CryptoCurrency>> call, Response<List<CryptoCurrency>> response) {

                CryptoView view = getView();
                if (view != null) {

                    view.hideLoading();

                    if (response.code() != 200) {
                        view.showErrorMessage(String.format("Error: %d", response.code()));
                    } else {

                        List<ViewModel> viewModels = new ArrayList<>();
                        viewModels.add(new SubheaderView("Top 100"));
                        viewModels.addAll(response.body()
                                .stream()
                                .map(CryptoViewModel::new)
                                .collect(Collectors.toList()));
                        view.showViewModels(viewModels);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CryptoCurrency>> call, Throwable throwable) {
                CryptoView view = getView();
                if (view != null) {
                    view.hideLoading();
                    view.showErrorMessage(String.format("Error: %s", throwable.getMessage()));
                }
            }
        });
    }

}