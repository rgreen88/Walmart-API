package com.example.rynel.walmartapp.data;

/**
 * Created by rynel on 10/16/2017.
 */

import com.example.rynel.walmartapp.model.WalmartLookup;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rynel on 10/13/2017.
 */

public class RemoteDataSource {

    //Walmart URL
    public static final String BASE_URL = "http://api.walmartlabs.com/v1/";

    //Retrofit construct
    public static Retrofit create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public static Observable<WalmartLookup> getWalmartLookup( String query, int start ) {
        Retrofit retrofit = create();
        RemoteService remoteService = retrofit.create( RemoteService.class );

        return remoteService.getWalmartLookup( query, start );
    }
}


//default search api: http://api.walmartlabs.c\
