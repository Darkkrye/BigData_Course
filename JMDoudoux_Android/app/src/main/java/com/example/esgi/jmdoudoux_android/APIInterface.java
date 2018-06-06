package com.example.esgi.jmdoudoux_android;
import java.util.List;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by meryl on 06/06/2018.
 */

public interface APIInterface {

    public static final String ENDPOINT = "10.33.3.96:8080";

    @GET("/ngrams")
    List<Gram> searchGramss(@Query("q") String query);
}