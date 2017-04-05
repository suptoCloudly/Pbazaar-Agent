package com.pbazaar.pbazaarforagent.ui.registration;

import android.util.Log;

import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.model.RegistrationDataModel;

import java.util.ArrayList;

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
    public void onRegistrationButtonClicked(final RegistrationDataModel registrationDataModel) {

        view.setLoadingIndicator(true);

        RegistrationRemoteService.getInstance().startRegistration(registrationDataModel, new RegistrationRemoteService.RegistrationCompletionListener() {
            @Override
            public void onRegistrationSuccess(String message) {
                view.onRegistrationSuccess(registrationDataModel.getEmail(), registrationDataModel.getPassword());

                view.setLoadingIndicator(false);
            }

            @Override
            public void onRegistrationFailed(String message) {
                view.showMessage(message);

                view.setLoadingIndicator(false);
            }
        });

    }

    @Override
    public void onCountrySelected(int countryId) {


        RegistrationRemoteService.getInstance().getDistrictByCountryId(countryId, new RegistrationRemoteService.DistrictLoadCompletionListener() {
            @Override
            public void onDistrictLoadSuccess(ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList) {
                view.onDistrictLoaded(spinnerDataModelArrayList);
            }

            @Override
            public void onDistrictLoadFailed(String message) {
                //  view.showMessage(message);
                Log.d(TAG, "Error: " + message);
            }
        });
    }

    @Override
    public void onDistrictSelected(int districtId) {

        Log.d(TAG, "District id: " + districtId);

        RegistrationRemoteService.getInstance().getthanaByDistrictId(districtId, new RegistrationRemoteService.ThanaLoadCompletionListener() {
            @Override
            public void onThanaLoadSuccess(ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList) {
                view.onThanaLoaded(spinnerDataModelArrayList);
            }

            @Override
            public void onThanaloadFailed(String message) {
                //  view.showMessage(message);
                Log.d(TAG, "Error: " + message);
            }
        });


    }
}
