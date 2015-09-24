package com.stonevalleypartners.doghouse.network;

import com.stonevalleypartners.doghouse.model.Dog;
import com.stonevalleypartners.doghouse.model.Vote;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by lindenle on 9/23/15.
 */

public interface DogHouseApi {
    @GET("/dogs")
    Dog.AllDogList dogs();

    @GET("/dogs/{breed}")
    Dog.BreedList breed(@Path("breed") String breed);

    @POST("/dog/{id}")
    Vote vote(@Path("id") String id);// Need to be able to send body, @Body("action") String action, @Body("clientID") String clientID);
}
