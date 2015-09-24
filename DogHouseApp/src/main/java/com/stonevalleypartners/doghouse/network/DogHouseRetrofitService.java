package com.stonevalleypartners.doghouse.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import roboguice.util.temp.Ln;

public class DogHouseRetrofitService extends RetrofitGsonSpiceService {

    final static String BASE_URL = "http://10.0.3.2:10000";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(DogHouseApi.class);
    }
    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        RestAdapter.Builder restAdapterBuilder = super.createRestAdapterBuilder();
        restAdapterBuilder.setLogLevel(RestAdapter.LogLevel.HEADERS);
        Executor executor = Executors.newCachedThreadPool();
        restAdapterBuilder.setExecutors(executor, executor);
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            File cacheDir = new File(getApplicationContext().getCacheDir(), UUID.randomUUID().toString());
            final Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);
            OkClient client = new OkClient(okHttpClient);
            restAdapterBuilder.setClient(client);
            okHttpClient.setCache(cache);
        } catch (Exception e) {
            Ln.e(e);
        }
        return restAdapterBuilder;
    }
    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

}
