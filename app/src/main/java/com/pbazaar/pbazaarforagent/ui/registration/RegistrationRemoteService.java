package com.pbazaar.pbazaarforagent.ui.registration;

import android.util.Log;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.AppController;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.RegistrationDataModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetThanaByDistrictIdResponse;
import com.pbazaar.pbazaarforagent.remote.data.RegistrationRequest;
import com.pbazaar.pbazaarforagent.remote.data.RegistrationResponse;
import com.pbazaar.pbazaarforagent.remote.data.ValidateReferralRequest;
import com.pbazaar.pbazaarforagent.remote.data.ValidateReferralResponse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Supto on 4/4/2017.
 */

public class RegistrationRemoteService {

    private static final String TAG = RegistrationRemoteService.class.getSimpleName();


    private static RegistrationRemoteService instance;

    private RegistrationRemoteService() {

    }

    public static RegistrationRemoteService getInstance() {
        if (instance == null) {
            instance = new RegistrationRemoteService();
        }
        return instance;
    }

    public void registerAgent(RegistrationDataModel model, final RegistrationCompletionListener registrationCompletionListener) {

        // check internet connection and return if not available
        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            if (registrationCompletionListener != null)
                registrationCompletionListener.onRegistrationFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }


        final RegistrationRequest request = new RegistrationRequest(RemoteConstant.PUBLIC_API_TOKEN, model.getEmail(), "", model.getPassword(), model.getGender(), model.getFirstName(), model.getLastName(), "", model.getStreetAddress(), model.getStreetAddressTwo(), 3, model.getDistrictId(), model.getThanaId(), "", model.getMobileNumber(), "", false, model.isAgentForData(), model.isAgentForSearch(), model.isAgentForAgency(), model.getReferral());

        if (model.getReferral().length() > 0 && !model.getReferral().contentEquals("")) {
            // TODO write referral validation code
            ValidateReferralRequest validateReferralRequest = new ValidateReferralRequest(RemoteConstant.PUBLIC_API_TOKEN, model.getReferral());

            Call<ValidateReferralResponse> validateReferralCall = PbazaarApi.getInstance().getPbazaarApiServiceClient().validateReferral(validateReferralRequest);
            validateReferralCall.enqueue(new Callback<ValidateReferralResponse>() {
                @Override
                public void onResponse(Call<ValidateReferralResponse> call, Response<ValidateReferralResponse> response) {
                    if (response.isSuccessful()) {

                        ValidateReferralResponse validateReferralResponse = response.body();

                        if (validateReferralResponse.getSuccess() == 1) {
                            requestRegistration(registrationCompletionListener, request);
                        } else {
                            if (registrationCompletionListener != null)
                                registrationCompletionListener.onInvalidReferralCodeGiven(AppController.getInstance().getString(R.string.invalid_referral_code_error_message));
                        }

                    }
                }

                @Override
                public void onFailure(Call<ValidateReferralResponse> call, Throwable t) {
                    if (registrationCompletionListener != null)
                        registrationCompletionListener.onRegistrationFailed(AppController.getInstance().getString(R.string.no_internet_error_message));

                }
            });

        } else {
            requestRegistration(registrationCompletionListener, request);
        }


    }

    private void requestRegistration(final RegistrationCompletionListener registrationCompletionListener, RegistrationRequest request) {
        Call<RegistrationResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().registerAgent(request);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful()) {
                    RegistrationResponse registrationResponse = response.body();

                    Log.d(TAG, "Registration response: " + registrationResponse.getSuccess());
                    if (registrationResponse.getSuccess() == 1) {
                        Log.d(TAG, "Reg: " + registrationResponse.getData());

                        if (registrationCompletionListener != null)
                            registrationCompletionListener.onRegistrationSuccess(registrationResponse.getData());
                    } else {
                        Log.d(TAG, "Reg: " + registrationResponse.getMessage().get(0));
                        if (registrationCompletionListener != null)
                            registrationCompletionListener.onRegistrationFailed(registrationResponse.getMessage().get(0));
                    }
                }

                Log.d(TAG, "Response: " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                if (registrationCompletionListener != null)
                    registrationCompletionListener.onRegistrationFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            }
        });
    }

    public void getDistrictByCountryId(int countryId, final DistrictLoadCompletionListener districtLoadCompletionListener) {
        // check internet connection and return if not available
        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            if (districtLoadCompletionListener != null)
                districtLoadCompletionListener.onDistrictLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));

            return;
        }

//        final GetDistrictByCountryIdRequest getDistrictByCountryIdRequest = new GetDistrictByCountryIdRequest(RemoteConstant.PUBLIC_API_TOKEN, countryId);
//        final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<LocationSpinnerDataModel>();

//        final Call<GetDistrictByCountryIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getDistrictsByCountryId(getDistrictByCountryIdRequest);
//        call.enqueue(new Callback<GetDistrictByCountryIdResponse>() {
//            @Override
//            public void onResponse(Call<GetDistrictByCountryIdResponse> call, Response<GetDistrictByCountryIdResponse> response) {
//                if (response.isSuccessful()) {
//
//                    GetDistrictByCountryIdResponse districtByCountryIdResponse = response.body();
//
//                    // if the request returns data then send it to view or show error message
//                    if (districtByCountryIdResponse.getSuccess() == 1) {
//                        for (GetDistrictByCountryIdResponse.Data data : districtByCountryIdResponse.getData()) {
//                            LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
//                            spinnerDataModelArrayList.add(locationSpinnerDataModel);
//                        }
//
//                        if (districtLoadCompletionListener != null)
//                            districtLoadCompletionListener.onDistrictLoadSuccess(spinnerDataModelArrayList);
//                    } else {
//                        if (districtLoadCompletionListener != null)
//                            districtLoadCompletionListener.onDistrictLoadFailed(districtByCountryIdResponse.getData());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetDistrictByCountryIdResponse> call, Throwable t) {
//                if (districtLoadCompletionListener != null)
//                    districtLoadCompletionListener.onDistrictLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
//            }
//        });

        final GetDistrictByCountryIdRequest getDistrictByCountryIdRequest = new GetDistrictByCountryIdRequest(RemoteConstant.PUBLIC_API_TOKEN, countryId);
        final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<LocationSpinnerDataModel>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                int retryCount = 0;
                while (true) {

                    try {
                        final Call<GetDistrictByCountryIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getDistrictsByCountryId(getDistrictByCountryIdRequest);
                        Response<GetDistrictByCountryIdResponse> response = call.execute();
                        if (response.isSuccessful()) {
                            GetDistrictByCountryIdResponse districtByCountryIdResponse = response.body();

                            // if the request returns data then send it to view or show error message
                            if (districtByCountryIdResponse.getSuccess() == 1) {
                                for (GetDistrictByCountryIdResponse.Data data : districtByCountryIdResponse.getData()) {
                                    LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
                                    spinnerDataModelArrayList.add(locationSpinnerDataModel);
                                }

                                if (districtLoadCompletionListener != null)
                                    districtLoadCompletionListener.onDistrictLoadSuccess(spinnerDataModelArrayList);
                            } else {
                                if (districtLoadCompletionListener != null)
                                    districtLoadCompletionListener.onDistrictLoadFailed(districtByCountryIdResponse.getMessage());

                            }
                            Log.d(TAG, "Response success");

                            break;
                        } else {

                            retryCount++;
                            Log.d(TAG, "Response unsuccess");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d(TAG, "Response fail");
                        retryCount++;
                    }

                    if (retryCount > RemoteConstant.RETRY_ATTEMPT) {
                        if (districtLoadCompletionListener != null)
                            districtLoadCompletionListener.onDistrictLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));

                        break;
                    }

                }
            }
        }).start();


    }

    public void getThanaByDistrictId(final int districtId, final ThanaLoadCompletionListener thanaLoadCompletionListener) {

        final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<>();

        if (districtId == -1) {
            if (thanaLoadCompletionListener != null)
                thanaLoadCompletionListener.onThanaLoadSuccess(spinnerDataModelArrayList);
            return;
        }

        // check internet connection and return if not available
        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            if (thanaLoadCompletionListener != null) {
                thanaLoadCompletionListener.onThanaloadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            }
            return;
        }


        new Thread(new Runnable() {
            @Override
            public void run() {

                int retryCount = 0;
                while (true) {

                    final GetThanaByDistrictIdRequest getThanaByDistrictIdRequest = new GetThanaByDistrictIdRequest(RemoteConstant.PUBLIC_API_TOKEN, districtId);
                    Call<GetThanaByDistrictIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getThanaByDistrictId(getThanaByDistrictIdRequest);

                    try {
                        Response<GetThanaByDistrictIdResponse> response = call.execute();
                        if (response.isSuccessful()) {
                            GetThanaByDistrictIdResponse getThanaByDistrictIdResponse = response.body();

                            if (getThanaByDistrictIdResponse.getSuccess() == 1) {
                                for (GetThanaByDistrictIdResponse.Data data : getThanaByDistrictIdResponse.getData()) {
                                    LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
                                    spinnerDataModelArrayList.add(locationSpinnerDataModel);
                                }

                                if (thanaLoadCompletionListener != null)
                                    thanaLoadCompletionListener.onThanaLoadSuccess(spinnerDataModelArrayList);
                            } else {
                                if (thanaLoadCompletionListener != null)
                                    thanaLoadCompletionListener.onThanaloadFailed(getThanaByDistrictIdResponse.getMessage());
                            }
                            break;
                        } else {
                            retryCount++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        retryCount++;
                    }

                    if (retryCount > RemoteConstant.RETRY_ATTEMPT) {
                        if (thanaLoadCompletionListener != null)
                            thanaLoadCompletionListener.onThanaloadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
                        break;
                    }

                }
            }
        }).start();

//        final GetThanaByDistrictIdRequest getThanaByDistrictIdRequest = new GetThanaByDistrictIdRequest(RemoteConstant.PUBLIC_API_TOKEN, districtId);
//        Call<GetThanaByDistrictIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getThanaByDistrictId(getThanaByDistrictIdRequest);

//        call.enqueue(new Callback<GetThanaByDistrictIdResponse>() {
//            @Override
//            public void onResponse(Call<GetThanaByDistrictIdResponse> call, Response<GetThanaByDistrictIdResponse> response) {
//                if (response.isSuccessful()) {
//                    GetThanaByDistrictIdResponse getThanaByDistrictIdResponse = response.body();
//
//                    if (getThanaByDistrictIdResponse.getSuccess() == 1) {
//                        for (GetThanaByDistrictIdResponse.Data data : getThanaByDistrictIdResponse.getData()) {
//                            LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
//                            spinnerDataModelArrayList.add(locationSpinnerDataModel);
//                        }
//
//                        if (thanaLoadCompletionListener != null)
//                            thanaLoadCompletionListener.onThanaLoadSuccess(spinnerDataModelArrayList);
//                    } else {
//                        if (thanaLoadCompletionListener != null)
//                            thanaLoadCompletionListener.onThanaloadFailed(getThanaByDistrictIdResponse.getData());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetThanaByDistrictIdResponse> call, Throwable t) {
//                if (thanaLoadCompletionListener != null)
//                    thanaLoadCompletionListener.onThanaloadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
//            }
//        });


    }


    public interface RegistrationCompletionListener {
        void onRegistrationSuccess(String message);

        void onRegistrationFailed(String message);

        void onInvalidReferralCodeGiven(String message);
    }

    public interface DistrictLoadCompletionListener {
        void onDistrictLoadSuccess(ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList);

        void onDistrictLoadFailed(String message);
    }

    public interface ThanaLoadCompletionListener {
        void onThanaLoadSuccess(ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList);

        void onThanaloadFailed(String message);
    }

}
