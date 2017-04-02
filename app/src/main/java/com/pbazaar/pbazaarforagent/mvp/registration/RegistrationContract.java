package com.pbazaar.pbazaarforagent.mvp.registration;

import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;
import com.pbazaar.pbazaarforagent.mvp.BasePresenter;
import com.pbazaar.pbazaarforagent.mvp.BaseView;

import java.util.ArrayList;

/**
 * Created by supto on 4/2/17.
 */

public interface RegistrationContract {
    interface View extends BaseView<Presenter> {

        void onRegistrationSuccess();

        void setLoadingIndicator(boolean status);

        void showMessage(String message);

        void onDistrictLoaded(ArrayList<LocationSpinnerDataModel> districtList);

        void onThanaLoaded(ArrayList<String> thanaList);

    }

    interface Presenter extends BasePresenter {

        void onRegistrationButtonClicked();

        void onCountrySelected(int countryId);

        void onDistrictSelected(int districtId);
    }
}
