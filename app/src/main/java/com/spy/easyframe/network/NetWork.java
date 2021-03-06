package com.spy.easyframe.network;

import com.spy.easyframe.network.api.BaseApi;
import com.spy.easyframe.network.api.ChartApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
* 网络层实例
* @date: 2016/11/10 15:18
* @author: KJL
* @param:
* @return:
*/
public class NetWork {
    private static BaseApi bannerApi;
    private static ChartApi chartApi;
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().
            setLevel(HttpLoggingInterceptor.Level.BASIC);
    private static OkHttpClient okHttpClient=new OkHttpClient.Builder().
            addInterceptor(httpLoggingInterceptor).build();
    private static Converter.Factory gsonConverterFactory= GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory= RxJavaCallAdapterFactory.create();
    private static Retrofit retrofit;
    private static final String real_host="http://www.qilin99.com";
    private static final String virtual_host="http://wp.500win.cn";


    public static BaseApi getBaseApi(){
        if (bannerApi==null){
            bannerApi=getRetrofit().create(BaseApi.class);
        }
        return bannerApi;
    }

    public static ChartApi getChartApi(){
        if (chartApi==null) {
            chartApi=getRetrofit().create(ChartApi.class);
        }
        return chartApi;
    }

    private static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(real_host)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
        }
        return retrofit;
    }
}
