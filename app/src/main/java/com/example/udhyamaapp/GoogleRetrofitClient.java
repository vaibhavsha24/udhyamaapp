package com.example.udhyamaapp;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by vishalverma on 13/02/18.
 */

public class GoogleRetrofitClient {

    private static Retrofit retrofit =  null;


    public static Retrofit getGoogleClient(String baseURL) {

        if (retrofit == null){

            retrofit  = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

        }
        return retrofit;

    }




}
