package com.pbazaar.pbazaarforagent.remote;

import com.pbazaar.pbazaarforagent.remote.data.CheckDuplicateNoRequest;
import com.pbazaar.pbazaarforagent.remote.data.CheckDuplicateNoResponse;
import com.pbazaar.pbazaarforagent.remote.data.ForgotPasswordRequest;
import com.pbazaar.pbazaarforagent.remote.data.ForgotPasswordResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetSubcategoryRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetSubcategoryResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.InsertImageResponse;
import com.pbazaar.pbazaarforagent.remote.data.LoginRequest;
import com.pbazaar.pbazaarforagent.remote.data.LoginResponse;
import com.pbazaar.pbazaarforagent.remote.data.PaymentHistoryRequest;
import com.pbazaar.pbazaarforagent.remote.data.PaymentHistoryResponse;
import com.pbazaar.pbazaarforagent.remote.data.PostHistoryRequest;
import com.pbazaar.pbazaarforagent.remote.data.PostHistoryResponse;
import com.pbazaar.pbazaarforagent.remote.data.PostProductRequest;
import com.pbazaar.pbazaarforagent.remote.data.PostProductResponse;
import com.pbazaar.pbazaarforagent.remote.data.RegistrationRequest;
import com.pbazaar.pbazaarforagent.remote.data.RegistrationResponse;
import com.pbazaar.pbazaarforagent.remote.data.ValidateReferralRequest;
import com.pbazaar.pbazaarforagent.remote.data.ValidateReferralResponse;

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

    @POST("ValidateReferralCode")
    Call<ValidateReferralResponse> validateReferral(@Body ValidateReferralRequest request);

    @POST("RegisterAgent")
    Call<RegistrationResponse> registerAgent(@Body RegistrationRequest request);

    @Multipart
    @POST("InsertPicture")
    Call<InsertImageResponse> uploadImage(@Part MultipartBody.Part file, @Part("apiToken") RequestBody requestBody);

    @POST("GetRequirementSubTypesByTypeId")
    Call<GetSubcategoryResponse> getSubcategory(@Body GetSubcategoryRequest request);

    @POST("InsertProductInfoByCollector")
    Call<PostProductResponse> postProduct(@Body PostProductRequest request);

    @POST("GetAgentPaymentHistory")
    Call<PaymentHistoryResponse> getPaymentHistory(@Body PaymentHistoryRequest request);

    @POST("ForgetPassword")
    Call<ForgotPasswordResponse> forgotPassword(@Body ForgotPasswordRequest request);

    @POST("GetProductInfoStatistics")
    Call<PostHistoryResponse> getPostHistory(@Body PostHistoryRequest request);

    @POST("CheckDuplicateNumber")
    Call<CheckDuplicateNoResponse> checkForDuplicateNo(@Body CheckDuplicateNoRequest request);
}
