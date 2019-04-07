package com.app.jjesusmp.googleplacesapi.data.remote.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit client;
    private final static String BASE_URL = "https://maps.googleapis.com";

    public static Retrofit getInstance() {
        if (client == null) {
            client= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return client;
    }
}