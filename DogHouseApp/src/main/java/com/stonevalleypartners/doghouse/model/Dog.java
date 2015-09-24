package com.stonevalleypartners.doghouse.model;

/**
 * Created by lindenle on 9/23/15.
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Dog implements Serializable {
    public String id;
    public String breed;
    public String url;
    public Number votes;
    public Boolean haveVoted;

    @SuppressWarnings("serial")
    public static class BreedList extends ArrayList<Dog> {
    }

    @SuppressWarnings("serial")
    public static class DogList {
        public String breed;
        public BreedList dogs;
    }

    @SuppressWarnings("serial")
    public static class AllDogList extends ArrayList<DogList>{}

    @Override
    public String toString(){
        return  this.id + " " + this.breed + ": " + this.url + "; " + this.votes;
    }
}