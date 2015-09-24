package com.stonevalleypartners.doghouse.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.stonevalleypartners.doghouse.model.Dog;
import com.stonevalleypartners.doghouse.model.Vote;

import roboguice.util.temp.Ln;

/**
 * Created by lindenle on 9/23/15.
 */

public class VoteRetrofitRequest extends RetrofitSpiceRequest<Vote, DogHouseApi> {
    public String mClientID;
    public String mAction;
    public String mDogID;

    public VoteRetrofitRequest(String dogID, String action, String clientID) {
        super(Vote.class, DogHouseApi.class);
        mClientID = clientID;
        mDogID=dogID;
        mAction = action;
    }

    @Override
    public Vote loadDataFromNetwork() {
        Ln.d("Call dogs web service");
        return getService().vote(mDogID); // how to send body, mAction, mClientID);
    }
}
