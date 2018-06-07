package com.example.esgi.jmdoudoux_android;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;


public interface NGramChannel {


    @GET("/ngrams")
    Call<List<NGram>> getAllGrams();


}
