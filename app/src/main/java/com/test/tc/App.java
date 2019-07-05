package com.test.tc;

import android.app.Application;

import com.test.tc.retrofit.RetrofitProvider;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitProvider.initialize();
    }
}
