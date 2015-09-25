package com.stonevalleypartners.doghouse.core;

import android.app.Application;

import com.stonevalleypartners.doghouse.BuildConfig;

import butterknife.ButterKnife;

/**
 * Created by lindenle on 9/24/15.
 */
public class DogHouseApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }
}
