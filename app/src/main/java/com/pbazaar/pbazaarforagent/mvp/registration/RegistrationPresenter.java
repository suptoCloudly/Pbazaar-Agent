package com.pbazaar.pbazaarforagent.mvp.registration;

import android.util.Log;

import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.remote.PbazaarApi;
import com.pbazaar.pbazaarforagent.remote.RemoteConstant;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdRequest;
import com.pbazaar.pbazaarforagent.remote.data.GetDistrictByCountryIdResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by supto on 4/2/17.
 */

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private static String TAG = RegistrationPresenter.class.getSimpleName();


    private RegistrationContract.View view;

    private RegistrationPresenter(RegistrationContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }


    public static RegistrationPresenter getInstance(RegistrationContract.View view) {
        return new RegistrationPresenter(view);
    }


    @Override
    public void start() {
        onCountrySelected(3);
    }

    @Override
    public void end() {

    }

    @Override
    public void onRegistrationButtonClicked() {
        view.showMessage("Fuck you");
    }

    @Override
    public void onCountrySelected(int countryId) {

        GetDistrictByCountryIdRequest getDistrictByCountryIdRequest = new GetDistrictByCountryIdRequest(RemoteConstant.PUBLIC_API_TOKEN, countryId);

        Call<GetDistrictByCountryIdResponse> call = PbazaarApi.getInstance().getPbazaarApiServiceClient().getDistrictsByCountryId(getDistrictByCountryIdRequest);
        call.enqueue(new Callback<GetDistrictByCountryIdResponse>() {
            @Override
            public void onResponse(Call<GetDistrictByCountryIdResponse> call, Response<GetDistrictByCountryIdResponse> response) {
                if (response.isSuccessful()) {

                    GetDistrictByCountryIdResponse districtByCountryIdResponse = response.body();

                    ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList = new ArrayList<LocationSpinnerDataModel>();

                    if (districtByCountryIdResponse.getSuccess() == 1) {
                        for (GetDistrictByCountryIdResponse.Data data : districtByCountryIdResponse.getData()) {
                            LocationSpinnerDataModel locationSpinnerDataModel = new LocationSpinnerDataModel(data.getName(), data.getId());
                            spinnerDataModelArrayList.add(locationSpinnerDataModel);
                        }
                        view.onDistrictLoaded(spinnerDataModelArrayList);
                    }

                }
            }

            @Override
            public void onFailure(Call<GetDistrictByCountryIdResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDistrictSelected(int districtId) {

    }
}
