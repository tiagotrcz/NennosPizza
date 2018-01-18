package com.gft.menospizza.provider;

import com.gft.menospizza.util.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiago on 16/01/18.
 */

public abstract class BaseRestProvider {

    protected Retrofit retrofit;

    public BaseRestProvider(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(Constants.READ_TIMEOUT_VALUE, Constants.READ_TIMEOUT_UNIT);
        httpClient.connectTimeout(Constants.CONNECT_TIMEOUT_VALUE, Constants.CONNECT_TIMEOUT_UNIT);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

}
