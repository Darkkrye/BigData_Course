package com.example.esgi.jmdoudoux_android;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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

    private static final String BASE_URL = "http://10.33.0.39:8080";

    private static Retrofit mRetrofit = null;
    private static NGramChannel mNGramChannel = null;

    public static ArrayList<NGram> mCurrentNGram;


    public static void init( ) {
        // Gson gson = new GsonBuilder( ).setDateFormat( "yyyy-MM-dd'T'HH:mm:ssZ" ).create( );

        final OkHttpClient okClient = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl( BASE_URL )
                .client(okClient)
                .build( );
        Log.e("retro", "build ok");
        mNGramChannel = mRetrofit.create( NGramChannel.class );

    }


    public static NGramChannel getmNGramChannel( ) {
        if( mNGramChannel == null ) {
            init( );
        }
        return mNGramChannel;
    }

    public static void getNGram( final INGram listener ) {
        Log.e("RETROFIT", "onresponse begin");
        if (listener != null) {
            Log.e("RETROFIT", "onresponse begin listener not null");
            RetrofitHelper.getmNGramChannel().getAllGrams().enqueue(new Callback<ArrayList<NGram>>() {

                @Override
                public void onResponse(Call<ArrayList<NGram>> call, Response<ArrayList<NGram>> response) {

                    Log.e("RETROFIT", "result : "+response.body().toString());
                    if (response != null) {

                        mCurrentNGram = response.body();

                        listener.onRetrofitResult(true, mCurrentNGram);
                    } else {
                        Log.e("RETROFIT", "response null");
                        listener.onRetrofitResult(false, null);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<NGram>> call, Throwable t) {
                    Log.e("RETROFIT", t.getMessage());
                    listener.onRetrofitResult(false, null);

                }
            });


        }


    }
}

