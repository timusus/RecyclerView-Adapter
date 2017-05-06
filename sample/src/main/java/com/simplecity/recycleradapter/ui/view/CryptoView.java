package com.simplecity.recycleradapter.ui.view;

import com.simplecity.recycleradapter.model.CryptoCurrency;
import com.simplecityapps.recycler_adapter.model.ViewModel;

import java.util.List;

public interface CryptoView {

    void showLoading();

    void hideLoading();

    void showViewModels(List<ViewModel> viewModels);

    void showErrorMessage(String message);

    void showPopup(CryptoCurrency cryptoCurrency);

}
