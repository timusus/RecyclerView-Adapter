package com.simplecity.recycleradapter.network;

import com.simplecity.recycleradapter.model.CryptoCurrency;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinMarketCapApi {

    @GET("v1/ticker/")
    Call<List<CryptoCurrency>> getCoinList(@Query("limit") int limit);

}