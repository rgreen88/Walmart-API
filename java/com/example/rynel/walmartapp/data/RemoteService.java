package com.example.rynel.walmartapp.data;

/**
 * Created by rynel on 10/16/2017.
 */

import com.example.rynel.walmartapp.model.WalmartLookup;
import com.example.rynel.walmartapp.util.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rynel on 10/13/2017.
 */

public interface RemoteService {

    @GET("search?format=json&apiKey="  + Constants.VALUES.API_KEY)
    Observable<WalmartLookup> getWalmartLookup(@Query("query") String query, @Query("start") int start);

}
