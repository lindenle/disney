package com.stonevalleypartners.doghouse.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.stonevalleypartners.doghouse.model.Dog;

import roboguice.util.temp.Ln;

/**
 * Created by lindenle on 9/23/15.
 */

public class DogListRetrofitRequest extends RetrofitSpiceRequest<Dog.AllDogList, DogHouseApi> {

    private String mClientID;

    public DogListRetrofitRequest(String clientID) {
        super(Dog.AllDogList.class, DogHouseApi.class);
        mClientID = clientID;
    }

    @Override
    public Dog.AllDogList loadDataFromNetwork() {
        Ln.d("Call dogs web service");
        return getService().dogs(mClientID);
    }
}
