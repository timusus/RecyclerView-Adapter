package com.simplecity.recycleradapter.dagger;

import com.simplecity.recycleradapter.ui.activity.CryptoActivity;
import com.simplecity.recycleradapter.ui.presenter.CryptoPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(CryptoActivity target);

    void inject(CryptoPresenter target);
}