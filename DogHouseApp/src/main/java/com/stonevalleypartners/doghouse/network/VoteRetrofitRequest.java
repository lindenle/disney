package com.stonevalleypartners.doghouse.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.stonevalleypartners.doghouse.model.Dog;
import com.stonevalleypartners.doghouse.model.Vote;

import roboguice.util.temp.Ln;

/**
 * Created by lindenle on 9/23/15.
 */

public class VoteRetrofitRequest extends RetrofitSpiceRequest<Dog, DogHouseApi> {
    public String mDogID;
    public Vote mVote = new Vote();

    public VoteRetrofitRequest(String dogID, String action, String clientID) {
        super(Dog.class, DogHouseApi.class);
        mVote.clientID = clientID;
        mVote.action = action;
        mDogID=dogID;
    }

    @Override
    public Dog loadDataFromNetwork() {
        Ln.d("Call dogs web service");
        return getService().vote(mDogID, mVote);
    }
}
