package com.example.retrofitapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRetrofitInstance {
    private static Retrofit retrofit=null;
    private static String BASE_URL="https://api.printful.com/";

    public static GetRetrofitService result(){
        if(retrofit==null){
            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GetRetrofitService.class);
    }
}
