package com.simplecity.recycleradapter;

import android.app.Application;

import com.simplecity.recycleradapter.dagger.AppComponent;
import com.simplecity.recycleradapter.dagger.AppModule;
import com.simplecity.recycleradapter.dagger.DaggerAppComponent;


public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initDagger(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(App application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
