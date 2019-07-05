package com.test.tc.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("2018/01/22/life-as-an-android-engineer/")
    Observable<RetrofitProvider.Page> getTenthChar();

    @GET("2018/01/22/life-as-an-android-engineer/")
    Observable<RetrofitProvider.Page> getEveryTenthChar();

    @GET("2018/01/22/life-as-an-android-engineer/")
    Observable<RetrofitProvider.Page> getUniqueChar();

}
