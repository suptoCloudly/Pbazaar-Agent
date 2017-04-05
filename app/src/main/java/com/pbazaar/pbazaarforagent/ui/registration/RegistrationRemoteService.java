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

    public void startRegistration(RegistrationDataModel model, final RegistrationCompletionListener registrationCompletionListener) {

        // check internet connection and return if not available
        if (!PreferenceHelper.getInstance().getNetworkStatus()) {
            if (registrationCompletionListener != null)
                registrationCompletionListener.onRegistrationFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            return;
        }


        RegistrationRequest registrationRequest = new RegistrationRequest(
                RemoteConstant.PUBLIC_API_TOKEN, model.getFirstName(), model.getLastName(), model.getUserName(),
                model.getEmail(), model.getGender(), model.isIndividualForRent(), model.isIndividualForSell(), model.isRealEstateCompany(),
                model.isHotelGuestHouse(), model.isAgentForSell(), model.isAgentForRent(), model.getCompany(), model.getCountryId(),
                model.getDistrictId(), model.getThanaAreaId(), model.getStreetAddress(), model.getStreetAddress2(),
                model.getMobile(), model.getMobile2(), model.getPhone(), model.isSubscribeNewsletter(), model.getPassword());

        Call<RegistrationResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().startRegistration(registrationRequest);
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

        GetDistrictByCountryIdRequest getDistrictByCountryIdRequest = new GetDistrictByCountryIdRequest(RemoteConstant.PUBLIC_API_TOKEN, countryId);
        final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<LocationSpinnerDataModel>();

        Call<GetDistrictByCountryIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getDistrictsByCountryId(getDistrictByCountryIdRequest);
        call.enqueue(new Callback<GetDistrictByCountryIdResponse>() {
            @Override
            public void onResponse(Call<GetDistrictByCountryIdResponse> call, Response<GetDistrictByCountryIdResponse> response) {
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
                }
            }

            @Override
            public void onFailure(Call<GetDistrictByCountryIdResponse> call, Throwable t) {
                if (districtLoadCompletionListener != null)
                    districtLoadCompletionListener.onDistrictLoadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            }
        });
    }

    public void getthanaByDistrictId(int districtId, final ThanaLoadCompletionListener thanaLoadCompletionListener) {

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


        final GetThanaByDistrictIdRequest getThanaByDistrictIdRequest = new GetThanaByDistrictIdRequest(RemoteConstant.PUBLIC_API_TOKEN, districtId);
        Call<GetThanaByDistrictIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getThanaByDistrictId(getThanaByDistrictIdRequest);

        call.enqueue(new Callback<GetThanaByDistrictIdResponse>() {
            @Override
            public void onResponse(Call<GetThanaByDistrictIdResponse> call, Response<GetThanaByDistrictIdResponse> response) {
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
                }
            }

            @Override
            public void onFailure(Call<GetThanaByDistrictIdResponse> call, Throwable t) {
                if (thanaLoadCompletionListener != null)
                    thanaLoadCompletionListener.onThanaloadFailed(AppController.getInstance().getString(R.string.no_internet_error_message));
            }
        });

    }


    public interface RegistrationCompletionListener {
        void onRegistrationSuccess(String message);

        void onRegistrationFailed(String message);
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
