package com.pbazaar.pbazaarforagent.ui.registration;

import android.os.Handler;
import android.util.Log;

import com.pbazaar.pbazaarforagent.helper.AppController;
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

    }

    @Override
    public void end() {

    }


    @Override
    public void onRegistrationButtonClicked(final RegistrationDataModel registrationDataModel) {
        view.setLoadingIndicator(true);

        RegistrationRemoteService.getInstance().registerAgent(registrationDataModel, new RegistrationRemoteService.RegistrationCompletionListener() {
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

            @Override
            public void onInvalidReferralCodeGiven(String message) {
                view.setLoadingIndicator(false);
                view.showMessage(message);
                view.onInvalidReferralCodeGiven();
            }
        });
    }

    @Override
    public void onCountrySelected(int countryId) {
        view.setLoadingIndicator(true);

        RegistrationRemoteService.getInstance().getDistrictByCountryId(countryId, new RegistrationRemoteService.DistrictLoadCompletionListener() {
            @Override
            public void onDistrictLoadSuccess(final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList) {

                new Handler(AppController.getInstance().getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.onDistrictLoaded(spinnerDataModelArrayList);
                        view.setLoadingIndicator(false);
                    }
                });


            }

            @Override
            public void onDistrictLoadFailed(final String message) {
                Log.d(TAG, "Error: " + message);

                new Handler(AppController.getInstance().getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(message);
                        view.setLoadingIndicator(false);
                    }
                });


            }

        });

    }

    @Override
    public void onDistrictSelected(int districtId) {

        Log.d(TAG, "District id: " + districtId);
        view.setLoadingIndicator(true);

        RegistrationRemoteService.getInstance().getThanaByDistrictId(districtId, new RegistrationRemoteService.ThanaLoadCompletionListener() {
            @Override
            public void onThanaLoadSuccess(final ArrayList<LocationSpinnerDataModel> spinnerDataModelArrayList) {

                new Handler(AppController.getInstance().getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.onThanaLoaded(spinnerDataModelArrayList);
                        view.setLoadingIndicator(false);
                    }
                });
            }

            @Override
            public void onThanaloadFailed(final String message) {

                Log.d(TAG, "Error: " + message);
                new Handler(AppController.getInstance().getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.setLoadingIndicator(false);
                        view.showMessage(message);
                    }
                });
            }
        });


    }
}
