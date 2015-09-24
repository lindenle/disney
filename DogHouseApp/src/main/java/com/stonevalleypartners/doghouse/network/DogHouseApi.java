package com.stonevalleypartners.doghouse.network;

import com.stonevalleypartners.doghouse.model.Dog;
import com.stonevalleypartners.doghouse.model.Vote;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by lindenle on 9/23/15.
 */

public interface DogHouseApi {
    @GET("/dogs")
    Dog.AllDogList dogs(@Query("clientID") String clientID);

    @GET("/dogs/{breed}")
    Dog.BreedList breed(@Path("breed") String breed, @Query("clientID") String clientID );

    @POST("/dog/{id}")
    Dog vote(@Path("id") String id, @Query("clientID") String clientID, @Query("action") String vote);
}
