package com.example.kygrykhon.knownuggettrial;

import com.google.gson.annotations.SerializedName;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kygrykhon on 5/11/2017.
 */

public class APIClient {

    public static String BaseURL = "http://139.59.4.157";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

class APIPermKey {
    @SerializedName("permKey")
    private String permKey;

    public APIPermKey(String permKey) {
        this.permKey = permKey;
    }

    public String getPermKey() {
        return permKey;
    }
}
