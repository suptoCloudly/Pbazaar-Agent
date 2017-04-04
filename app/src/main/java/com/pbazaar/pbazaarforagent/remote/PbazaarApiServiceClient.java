package com.pbazaar.pbazaarforagent.remote;

import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.LoginRequest;
import com.pbazaar.pbazaarforagent.remote.data.LoginResponse;
import com.pbazaar.pbazaarforagent.remote.data.RegistrationRequest;
import com.pbazaar.pbazaarforagent.remote.data.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by supto on 4/2/17.
 */

public interface PbazaarApiServiceClient {


    @POST("LogIn")
    Call<LoginResponse> logIn(@Body LoginRequest loginRequest);

    @POST("GetDistrictsByCountryId")
    Call<GetDistrictByCountryIdResponse> getDistrictsByCountryId(@Body GetDistrictByCountryIdRequest getDistrictByCountryIdRequest);

    @POST("GetThanaAreasByDistrictId")
    Call<GetThanaByDistrictIdResponse> getThanaByDistrictId(@Body GetThanaByDistrictIdRequest request);

    @POST("Register")
    Call<RegistrationResponse> startRegistration(@Body RegistrationRequest registrationRequest);

}
