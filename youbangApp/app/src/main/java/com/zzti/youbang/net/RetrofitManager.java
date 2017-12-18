package com.zzti.youbang.net;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zzti.youbang.app.AppConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * @author fengyonggge
 * @date 2017/2/7
 */
public class RetrofitManager {
    private static RetrofitManager mRetrofitManager;
    private Retrofit mRetrofit;

    private RetrofitManager(){
        initRetrofit();
    }

    public static synchronized RetrofitManager getInstance(){
        if (mRetrofitManager == null){
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }

    private void initRetrofit() {
        HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor();
        LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new RspCheckInterceptor());

        if (AppConfig.DEBUG){
            builder.addInterceptor(LoginInterceptor);
        }


        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setExclusionStrategies(new ExclusionStrategy() {
                                            @Override
                                            public boolean shouldSkipField(FieldAttributes f) {
                                                return false;
                                            }

                                            @Override
                                            public boolean shouldSkipClass(Class<?> clazz) {
                                                return false;
                                            }
                                        })
                .create();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.NET_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    public <T> T createReq(Class<T> reqServer){
        return mRetrofit.create(reqServer);
    }


}
