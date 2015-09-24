package com.stonevalleypartners.doghouse.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.stonevalleypartners.doghouse.model.Dog;

import roboguice.util.temp.Ln;

/**
 * Created by lindenle on 9/23/15.
 */

public class BreedListRetrofitRequest extends RetrofitSpiceRequest<Dog.BreedList, DogHouseApi> {
    private String mBreed;

    public BreedListRetrofitRequest(String breed) {
        super(Dog.BreedList.class, DogHouseApi.class);
        mBreed = breed;
    }


    @Override
    public Dog.BreedList loadDataFromNetwork() {
        Ln.d("Call breeds web service");
        return getService().breed(mBreed);
    }
}
