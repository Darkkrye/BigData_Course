package com.example.esgi.jmdoudoux_android;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitHelper.init();
        Log.e("Appli", "init ok");
    }
}
