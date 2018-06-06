package com.example.esgi.jmdoudoux_android;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Pico on 09/10/2017.
 */


public class RetrofitHelper {
    private static final String TAG = RetrofitHelper.class.getSimpleName( );

    private static final String BASE_URL = "http://10.33.3.96:8080/";

    private static Retrofit mRetrofit = null;
    private static NGramChannel mNGramChannel = null;

    public static List<NGram> mCurrentNGram;


    public static void init( ) {
        // Gson gson = new GsonBuilder( ).setDateFormat( "yyyy-MM-dd'T'HH:mm:ssZ" ).create( );

        mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl( BASE_URL ).build( );
        Log.e("retro", "build ok");
        mNGramChannel = mRetrofit.create( NGramChannel.class );

    }


    public static NGramChannel getmNGramChannel( ) {
        if( mNGramChannel == null ) {
            init( );
        }
        return mNGramChannel;
    }

    public static void getNGram( String gram , final INGram listener ) {
        if (listener != null) {
            RetrofitHelper.getmNGramChannel().getAllGrams().enqueue(new Callback<List<NGram>>() {
                @Override
                public void onResponse(Call<List<NGram>> call, Response<List<NGram>> response) {
                    if (response != null) {
                        List<NGram> channels = response.body();

                        if (channels != null) {
                            mCurrentNGram = new Gson().fromJson(Label.JSON_BOX, listType);;

                        } else {
                            listener.onRetrofitResult(false);
                        }
                    } else {
                        listener.onRetrofitResult(false);
                    }
                }

                @Override
                public void onFailure(Call<List<NGram>> call, Throwable t) {
                    Type listType = new TypeToken<ArrayList<NGram>>(){}.getType();

                    mCurrentNGram = new Gson().fromJson(Label.JSON_BOX, listType);
                    listener.onRetrofitResult(true);
                    //   t.printStackTrace();
                }
            });


        }


    }
}

