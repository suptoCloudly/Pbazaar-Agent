package com.pbazaar.pbazaarforagent.remote;

import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetSubcategoryFromCategoryRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetSubcategoryFromCategoryResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.InsertImageResponse;
import com.pbazaar.pbazaarforagent.remote.data.LoginRequest;
import com.pbazaar.pbazaarforagent.remote.data.LoginResponse;
import com.pbazaar.pbazaarforagent.remote.data.RegistrationRequest;
import com.pbazaar.pbazaarforagent.remote.data.RegistrationResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @Multipart
    @POST("InsertPicture")
    Call<InsertImageResponse> uploadImage(@Part MultipartBody.Part file, @Part("apiToken") RequestBody requestBody);

    @POST("GetCategories")
    Call<GetSubcategoryFromCategoryResponse> getSubcategoryFromCategory(@Body GetSubcategoryFromCategoryRequest request);
}
