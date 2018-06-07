package com.example.esgi.jmdoudoux_android;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;


public interface NGramChannel {


    @GET("/ngrams")
    Call<ArrayList<NGram>> getAllGrams();


}
