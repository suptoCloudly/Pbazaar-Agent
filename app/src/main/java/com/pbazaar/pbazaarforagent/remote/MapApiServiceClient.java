package com.pbazaar.pbazaarforagent.remote;


import com.pbazaar.pbazaarforagent.remote.data.GeoCodingResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by supto on 11/10/17.
 */

public interface MapApiServiceClient {

    @GET("geocode/json")
    Observable<GeoCodingResponse> getGeoCodingAddress(@Query("latlng") String latlng, @Query("key") String apiKey);

}
