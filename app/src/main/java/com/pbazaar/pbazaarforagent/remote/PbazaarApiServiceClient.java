package com.pbazaar.pbazaarforagent.remote;

import com.pbazaar.pbazaarforagent.remote.data.LoginRequest;
import com.pbazaar.pbazaarforagent.remote.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by supto on 4/2/17.
 */

public interface PbazaarApiServiceClient {


    @POST("LogIn")
    Call<LoginResponse> logIn(@Body LoginRequest loginRequest);

}
