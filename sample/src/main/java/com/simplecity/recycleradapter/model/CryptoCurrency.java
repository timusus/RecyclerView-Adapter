package com.simplecity.recycleradapter.model;


import com.google.gson.annotations.SerializedName;

public class CryptoCurrency {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("rank")
    public String rank;

    @SerializedName("market_cap_usd")
    public String marketCap;

    @SerializedName("percent_change_24h")
    public String percentChange24h;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CryptoCurrency that = (CryptoCurrency) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}