package com.imooc.mvp.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author   ： cxw
 * Date     ： 2022/4/22 21:04
 * Explain  :  retrofit工具类
 */
public class RetrofitClient {

    private String HOST_URL = "https://ab83ba10-cca9-4b0f-afae-ebe20c0a6c7b.mock.pstmn.io/";

    private Retrofit retrofit;

    private RetrofitClient() {
    }

    private static RetrofitClient mInstatnce;

    public static RetrofitClient getInstance() {
        if (mInstatnce == null) {
            synchronized (RetrofitClient.class) {
                if (mInstatnce == null) {
                    return new RetrofitClient();
                }
            }
        }

        return mInstatnce;
    }

    public <T> T getService(Class<T> cls) {


        return getRetrofit().create(cls);
    }

    private Retrofit getRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(HOST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

        }

        return retrofit;
    }

}
