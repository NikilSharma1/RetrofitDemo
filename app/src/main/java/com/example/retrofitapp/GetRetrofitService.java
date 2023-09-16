package com.example.retrofitapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRetrofitService {
    @GET("countries")
    Call<FullData>getService();
}
