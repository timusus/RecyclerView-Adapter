package com.simplecity.recycleradapter.dagger;

import android.util.Log;

import com.simplecity.recycleradapter.network.CoinMarketCapApi;

import java.net.InetSocketAddress;
import java.net.Proxy;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String TAG = "NetworkModule";

    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return "https://api.coinmarketcap.com/";
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(s -> Log.i(TAG, s));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    Proxy provideProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.4", 8888));
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    CoinMarketCapApi provideCryptoCompareApi(Retrofit retrofit) {
        return retrofit.create(CoinMarketCapApi.class);
    }

}