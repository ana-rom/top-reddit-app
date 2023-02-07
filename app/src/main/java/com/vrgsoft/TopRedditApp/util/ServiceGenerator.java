package com.vrgsoft.TopRedditApp.util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String BASE_URL = "https://www.reddit.com/";
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder().build();
    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OK_HTTP_CLIENT)
            .build();

    public static <T> T createService(Class<T> serviceClass) {
        return RETROFIT.create(serviceClass);
    }
}
