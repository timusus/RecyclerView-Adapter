package com.simplecity.recycleradapter.network;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.simplecity.recycleradapter.model.CoinListResponse;
import com.simplecity.recycleradapter.model.CryptoCurrency;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class CryptoDeserializer implements JsonDeserializer<CoinListResponse> {
    @Override
    public CoinListResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        CoinListResponse coinListResponse = new CoinListResponse();

        Gson gson = new Gson();

        Set<Map.Entry<String, JsonElement>> entries = json.getAsJsonObject().get("Data").getAsJsonObject().entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            JsonElement jsonElement = entry.getValue();
            if (jsonElement.isJsonObject()) {
                coinListResponse.coinList.add(gson.fromJson(jsonElement, CryptoCurrency.class));
            }
        }

        coinListResponse.coinList.sort(Comparator.comparing(a -> a.rank));

        return coinListResponse;
    }
}