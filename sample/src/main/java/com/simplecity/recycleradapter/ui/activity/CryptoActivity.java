package com.simplecity.recycleradapter.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.simplecity.recycleradapter.App;
import com.simplecity.recycleradapter.R;
import com.simplecity.recycleradapter.model.CryptoCurrency;
import com.simplecity.recycleradapter.ui.adapter.SectionedAdapter;
import com.simplecity.recycleradapter.ui.presenter.CryptoPresenter;
import com.simplecity.recycleradapter.ui.view.CryptoView;
import com.simplecity.recycleradapter.ui.viewmodel.LoadingViewModel;
import com.simplecityapps.recycler_adapter.adapter.ViewModelAdapter;
import com.simplecityapps.recycler_adapter.model.ViewModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoActivity extends AppCompatActivity implements CryptoView {

    @Inject CryptoPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ViewModelAdapter adapter;

    private LoadingViewModel loadingViewModel = new LoadingViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SectionedAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.bindView(this);
        presenter.getCryptoCurrencies();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.unbindView(this);
    }

    @Override
    public void showLoading() {
        adapter.setItems(Collections.singletonList(loadingViewModel));
    }

    @Override
    public void hideLoading() {
        adapter.setItems(Collections.emptyList());
    }

    @Override
    public void showViewModels(List<ViewModel> viewModels) {
        adapter.setItems(viewModels);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPopup(CryptoCurrency cryptoCurrency) {
        new AlertDialog.Builder(this)
                .setTitle(String.format("%s. %s", cryptoCurrency.rank, cryptoCurrency.symbol))
                .setMessage(String.format("-Name: %s\n-Market cap: $%s", cryptoCurrency.name, cryptoCurrency.marketCap))
                .show();
    }
}