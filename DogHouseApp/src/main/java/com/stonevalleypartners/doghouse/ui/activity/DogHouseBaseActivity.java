package com.stonevalleypartners.doghouse.ui.activity;

/**
 * Created by lindenle on 9/23/15.
 */

import android.support.v7.app.AppCompatActivity;

import com.octo.android.robospice.SpiceManager;
import com.stonevalleypartners.doghouse.network.DogHouseRetrofitService;

public class DogHouseBaseActivity extends AppCompatActivity {
    private SpiceManager spiceManager = new SpiceManager(DogHouseRetrofitService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
