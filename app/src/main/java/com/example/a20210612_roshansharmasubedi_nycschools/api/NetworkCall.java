package com.example.a20210612_roshansharmasubedi_nycschools.api;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkCall {

    private static GsonConverterFactory gsonConverterFactory;
    private static HttpLoggingInterceptor loggingInterceptor;
    private static RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

    public NetworkCall() {
        gsonConverterFactory = GsonConverterFactory.create();
        rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                String logName = "OKHTTP";
                if (message.startsWith("{")) {
                    Log.d(logName, message);
                } else {
                    Log.d(logName, message);
                }
            }
        });
    }
    Interceptor interceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request()
                    .newBuilder()
                    .addHeader("X-App-Token", "QKt0HcFXH1MF4ODuYmbTrlrhm")
                    .build();
            return chain.proceed(newRequest);
        }
    };

    public OkHttpClient.Builder httpClientBuilder(){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);
    }

    public Retrofit.Builder getRetrofitBuilder(OkHttpClient.Builder builder){
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl("https://data.cityofnewyork.us/resource/")
                .client(builder.build());
    }

    public <T> T create(Class<T> serviceClass){
        return getRetrofitBuilder(httpClientBuilder())
                .build().create(serviceClass);
    }

}
