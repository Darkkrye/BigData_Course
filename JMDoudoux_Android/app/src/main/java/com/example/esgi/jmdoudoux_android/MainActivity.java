package com.example.esgi.jmdoudoux_android;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements INGram{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("Main", "begining");


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        RetrofitHelper.getNGram("Paris", this);
    }

    @Override
    public void onRetrofitResult(boolean okay) {
        if(okay){
            Log.e("MAIN", "ok");
        }else{
            Log.e("MAIN", "ko");
        }
    }
}
