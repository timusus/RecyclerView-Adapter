package com.simplecity.recycleradapter.dagger;

import android.content.Context;

import com.simplecity.recycleradapter.ui.presenter.CryptoPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    CryptoPresenter provideCryptoPresenter(Context context) {
        return new CryptoPresenter(context);
    }

}
